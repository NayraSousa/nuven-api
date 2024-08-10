package com.example.nuvenapi.api.dto;

import com.example.nuvenapi.model.Address;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlaceOutputDTO {

    private String name;
    private Address address;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
