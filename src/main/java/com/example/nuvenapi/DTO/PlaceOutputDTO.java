package com.example.nuvenapi.DTO;

import com.example.nuvenapi.model.Address;

import java.time.LocalDateTime;

public class PlaceOutputDTO {

    private String name;
    private Address address;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
