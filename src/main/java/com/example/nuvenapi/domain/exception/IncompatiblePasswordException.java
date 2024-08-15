package com.example.nuvenapi.domain.exception;

public class IncompatiblePasswordException extends RuntimeException{
    public IncompatiblePasswordException (String message){
        super("Incompatible password");
    };
}
