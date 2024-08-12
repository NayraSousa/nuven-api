package com.example.nuvenapi.api.dto.mapper;

import com.example.nuvenapi.api.dto.PlaceInputDTO;
import com.example.nuvenapi.api.dto.PlaceOutputDTO;
import com.example.nuvenapi.api.dto.configuration.ModelMapperConfiguration;
import com.example.nuvenapi.domain.entity.Place;
import org.springframework.stereotype.Component;

@Component
public class PlaceMapper {

    private final ModelMapperConfiguration modelMapperConfiguration;

    public PlaceMapper(ModelMapperConfiguration modelMapperConfiguration){
        this.modelMapperConfiguration = modelMapperConfiguration;
    }

    public Place toEntity(PlaceInputDTO placeInputDTO){
        return modelMapperConfiguration.modelMapper().map(placeInputDTO, Place.class);
    }

    public PlaceOutputDTO toDTO(Place place){
        return modelMapperConfiguration.modelMapper().map(place, PlaceOutputDTO.class);
    }
}
