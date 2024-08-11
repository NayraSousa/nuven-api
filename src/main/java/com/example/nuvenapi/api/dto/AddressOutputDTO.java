package com.example.nuvenapi.api.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AddressOutputDTO {

    private UUID id;
    private String street;
    private String number;
    private String district;
    private String city;
    private String state;

}
