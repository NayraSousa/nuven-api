package com.example.nuvenapi.api.dto;

import com.example.nuvenapi.domain.entity.Address;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PlaceInputDTO {

    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private Address address;
    @NotNull
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
