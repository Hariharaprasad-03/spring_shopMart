package com.example.spring_jpa.exception;

public class UserAlreayExistExceptiom extends RuntimeException {
    public UserAlreayExistExceptiom(String message) {
        super(message);
    }
}
