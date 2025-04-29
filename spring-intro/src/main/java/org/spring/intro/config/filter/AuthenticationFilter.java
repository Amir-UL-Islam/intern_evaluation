package org.spring.intro.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.spring.intro.config.PasswordEncoder;
import org.spring.intro.config.context.SecurityContext;
import org.spring.intro.exception.AuthenticationException;
import org.spring.intro.model.entity.MUser;
import org.spring.intro.service.MUserService;

import java.io.IOException;

// AuthenticationFilter.java
public class AuthenticationFilter implements Filter {
    private final MUserService userService;
    private final PasswordEncoder passwordEncoder;
    private final SecurityContext securityContext;

    public AuthenticationFilter(MUserService userService,
                                PasswordEncoder passwordEncoder,
                                SecurityContext securityContext) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.securityContext = securityContext;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if ("/api/login".equals(httpRequest.getRequestURI()) &&
                "POST".equalsIgnoreCase(httpRequest.getMethod())) {
            try {
                // Extract credentials from request
                String username = httpRequest.getParameter("username");
                String password = httpRequest.getParameter("password");

                // Authenticate user
                MUser user = userService.findByUsername(username)
                        .orElseThrow(() -> new AuthenticationException("User not found"));

                if (!passwordEncoder.matches(password, user.getPassword())) {
                    throw new AuthenticationException("Invalid password");
                }

                // Create session and set security context
                HttpSession session = httpRequest.getSession(true);
                session.setAttribute("user", user);
                securityContext.setCurrentUser(user);

                // Return success response
                httpResponse.setStatus(HttpServletResponse.SC_OK);
                httpResponse.getWriter().write("Login successful");
                return;
            } catch (AuthenticationException e) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().write("Authentication failed: " + e.getMessage());
                return;
            }
        }

        chain.doFilter(request, response);
    }
}