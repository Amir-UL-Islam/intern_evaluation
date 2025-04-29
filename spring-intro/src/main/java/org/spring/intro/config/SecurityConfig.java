package org.spring.intro.config;

import org.spring.intro.config.context.SecurityContext;
import org.spring.intro.config.filter.AuthenticationFilter;
import org.spring.intro.config.filter.AuthorizationFilter;
import org.spring.intro.service.MUserService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// SecurityConfig.java
@Configuration
public class SecurityConfig {

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilter(
            MUserService userService,
            PasswordEncoder passwordEncoder,
            SecurityContext securityContext) {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticationFilter(userService, passwordEncoder, securityContext));
        registrationBean.addUrlPatterns("/api/login");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> authorizationFilter(
            SecurityContext securityContext,
            MUserService userService) {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationFilter(securityContext, userService));
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SimplePasswordEncoder();
    }

    @Bean
    public SecurityContext securityContext() {
        return new SecurityContext();
    }
}