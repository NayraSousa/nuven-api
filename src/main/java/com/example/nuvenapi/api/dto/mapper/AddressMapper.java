package com.example.nuvenapi.api.dto.mapper;

import com.example.nuvenapi.api.dto.AddressInputDTO;
import com.example.nuvenapi.api.dto.AddressOutputDTO;
import com.example.nuvenapi.api.dto.configuration.ModelMapperConfiguration;
import com.example.nuvenapi.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    private final ModelMapperConfiguration modelMapperConfiguration;

    public AddressMapper(ModelMapperConfiguration modelMapperConfiguration) {
        this.modelMapperConfiguration = modelMapperConfiguration;
    }

    public Address toEntity(AddressInputDTO addressInputDTO) {
        return modelMapperConfiguration.modelMapper().map(addressInputDTO, Address.class);
    }

    public AddressOutputDTO toDTO(Address address) {
        return modelMapperConfiguration.modelMapper().map(address, AddressOutputDTO.class);
    }
}
