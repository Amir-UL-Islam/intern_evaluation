package org.spring.intro.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.spring.intro.config.context.SecurityContext;
import org.spring.intro.model.entity.MUser;
import org.spring.intro.service.MUserService;

import java.io.IOException;

// AuthorizationFilter.java
public class AuthorizationFilter implements Filter {
    private final SecurityContext securityContext;
    private final MUserService userService;

    public AuthorizationFilter(SecurityContext securityContext, MUserService userService) {
        this.securityContext = securityContext;
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Skip authentication check for login and public endpoints
        if ("/api/login".equals(httpRequest.getRequestURI()) ||
                httpRequest.getRequestURI().startsWith("/api/public/")) {
            chain.doFilter(request, response);
            return;
        }

        // Check for existing session
        HttpSession session = httpRequest.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not authenticated");
            return;
        }

        // Set security context
        MUser user = (MUser) session.getAttribute("user");
        securityContext.setCurrentUser(user);

        // Check authorization for protected endpoints
        if (httpRequest.getRequestURI().startsWith("/api/admin/") &&
                !hasRole(user, "ADMIN")) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Insufficient privileges");
            return;
        }

        // Check CSRF token for state-changing methods
        if (isStateChangingMethod(httpRequest.getMethod())) {
            String csrfToken = httpRequest.getHeader("X-CSRF-TOKEN");
            String sessionToken = (String) session.getAttribute("csrfToken");

            if (csrfToken == null || !csrfToken.equals(sessionToken)) {
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid CSRF token");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean hasRole(MUser user, String roleName) {
        return user.getRoles().stream()
                .anyMatch(role -> role.getName().equals(roleName));
    }

    private boolean isStateChangingMethod(String method) {
        return "POST".equalsIgnoreCase(method) ||
                "PUT".equalsIgnoreCase(method) ||
                "DELETE".equalsIgnoreCase(method) ||
                "PATCH".equalsIgnoreCase(method);
    }
}