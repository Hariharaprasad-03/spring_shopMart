package com.example.spring_jpa.exception;

public class InValidCrediantialsException extends RuntimeException {
    public InValidCrediantialsException(String message) {
        super(message);
    }
}
