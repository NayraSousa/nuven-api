package com.example.nuvenapi.domain.exception;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String message){
        super(message);
    }

    public EntityNotFoundException(UUID id){
        this(String.format("Place with id: %s not found", id));
    }
}
