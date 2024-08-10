package com.example.nuvenapi.DTO;

import com.example.nuvenapi.model.Address;

import java.time.LocalDateTime;
import java.util.UUID;

public class PlaceInputDTO {

    private UUID id;
    private String name;
    private Address address;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
