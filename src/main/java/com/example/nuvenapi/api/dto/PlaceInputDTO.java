package com.example.nuvenapi.api.dto;

import com.example.nuvenapi.model.Address;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PlaceInputDTO {

    private UUID id;
    private String name;
    private Address address;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
