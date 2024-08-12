package com.example.nuvenapi.api.dto;

<<<<<<< HEAD
import jakarta.validation.Valid;
=======
>>>>>>> 3a446f0 (refactor: change dtos logic)
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class PlaceInputDTO {

    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    @Valid
    private AddressInputDTO address;

    @NotBlank
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
