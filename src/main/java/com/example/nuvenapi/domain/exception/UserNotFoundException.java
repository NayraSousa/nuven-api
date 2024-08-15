package com.example.nuvenapi.domain.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException (String message){
        super("User not found");
    };

}
