package com.healfiness.backend.core.domain.dto.exceptions;

public class InvalidCredentialsException extends Exception {

    public InvalidCredentialsException() {
        super("Invalid credentials provided.");
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }

}
