package com.example.nuvenapi.DTO;

import jakarta.persistence.Column;

import java.util.UUID;

public class AddressInputDTO {

    private UUID id;
    private String street;
    private String number;
    private String district;
    private String city;
    private String state;
}
