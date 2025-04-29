package org.spring.intro.exception;

// AuthenticationException.java
public class AuthenticationException extends Throwable {
    public AuthenticationException(String message) {
        super(message);
    }
}