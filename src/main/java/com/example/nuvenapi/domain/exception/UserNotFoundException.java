package com.example.nuvenapi.domain.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException (){
        super("User not found");
    };

}
