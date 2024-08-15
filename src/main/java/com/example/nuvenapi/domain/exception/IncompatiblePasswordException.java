package com.example.nuvenapi.domain.exception;

public class IncompatiblePasswordException extends RuntimeException{
    public IncompatiblePasswordException (){
        super("Incompatible password");
    };
}
