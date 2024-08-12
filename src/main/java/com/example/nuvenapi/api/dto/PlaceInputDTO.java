package com.example.nuvenapi.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PlaceInputDTO {

    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    private AddressInputDTO address;

    @NotBlank
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
