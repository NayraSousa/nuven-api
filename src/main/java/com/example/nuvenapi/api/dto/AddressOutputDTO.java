package com.example.nuvenapi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressOutputDTO {

    private UUID id;
    private String street;
    private String number;
    private String district;
    private String city;
    private String state;

}
