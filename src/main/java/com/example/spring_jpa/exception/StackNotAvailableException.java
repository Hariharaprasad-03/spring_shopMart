package com.example.spring_jpa.exception;

public class StackNotAvailableException extends RuntimeException {
    public StackNotAvailableException(String message) {
        super(message);
    }
}
