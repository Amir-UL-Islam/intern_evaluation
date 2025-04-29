package org.spring.intro.config;

// PasswordEncoder.java (your custom interface)
public interface PasswordEncoder {
    String encode(CharSequence rawPassword);
    boolean matches(CharSequence rawPassword, String encodedPassword);
}

