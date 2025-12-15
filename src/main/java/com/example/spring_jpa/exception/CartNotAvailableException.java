package com.example.spring_jpa.exception;

public class CartNotAvailableException extends RuntimeException {
    public CartNotAvailableException(String message) {
        super(message);
    }
}
