package com.example.spring_jpa.exception;

public class NoProductExistException extends RuntimeException{

    public NoProductExistException(String message){
        super(message);
    }
}
