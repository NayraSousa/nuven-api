package com.example.nuvenapi.api.dto;

import lombok.Data;

@Data
public class AddressOutputDTO {

    private String street;
    private String number;
    private String district;
    private String city;
    private String state;

}
