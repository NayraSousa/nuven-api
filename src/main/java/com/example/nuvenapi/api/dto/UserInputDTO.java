package com.example.nuvenapi.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserInputDTO {

    @NotNull
    private String username;
    @NotNull
    private String password;
}
