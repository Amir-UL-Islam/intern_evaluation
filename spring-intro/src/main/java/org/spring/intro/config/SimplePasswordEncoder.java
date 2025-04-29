package org.spring.intro.config;

// SimplePasswordEncoder.java
public class SimplePasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        // In a real application, you should add a salt and use a stronger hash
        return Integer.toString(rawPassword.hashCode());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
