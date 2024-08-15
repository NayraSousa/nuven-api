package com.example.nuvenapi.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
