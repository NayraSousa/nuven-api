package com.example.nuvenapi.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class AddressInputDTO {

    private UUID id;

    @NotBlank
    @NotNull
    private String street;
    @NotNull
    @NotBlank
    private String number;
    @NotNull
    @NotBlank
    private String district;
    @NotBlank
    @NotNull
    private String city;
    @NotNull
    @NotBlank
    private String state;
}
