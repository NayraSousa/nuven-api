package com.example.nuvenapi.domain.service;

import com.example.nuvenapi.api.dto.PlaceInputDTO;
import com.example.nuvenapi.api.dto.PlaceOutputDTO;
import com.example.nuvenapi.api.dto.mapper.PlaceMapper;
import com.example.nuvenapi.domain.entity.Place;
import com.example.nuvenapi.domain.exception.EntityNotFoundException;
import com.example.nuvenapi.domain.repository.PlaceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;

    public PlaceService(PlaceRepository placeRepository, PlaceMapper placeMapper) {
        this.placeRepository = placeRepository;
        this.placeMapper = placeMapper;
    }

    public PlaceOutputDTO create(PlaceInputDTO placeInputDTO) {
        new Place();
        Place place = placeMapper.toEntity(placeInputDTO);
        place.onCreate();
        place.onUpdate();
        place = placeRepository.save(place);
        return placeMapper.toDTO(place);

    }

    public List<PlaceOutputDTO> readAll() {
        List<Place> places = placeRepository.findAll();
        List<PlaceOutputDTO> placesOutputDTO = new ArrayList<>();
        placesOutputDTO = places
                .stream()
                .map(placeMapper::toDTO)
                .collect(Collectors.toList());
        return placesOutputDTO;

    }

    public List<PlaceOutputDTO> readByName(String name) {
        List<Place> placesByName = placeRepository.getByName(name);
        List<PlaceOutputDTO> placeOutputDTOS = new ArrayList<>();
        placeOutputDTOS = placesByName
                .stream()
                .map(placeMapper::toDTO)
                .collect(Collectors.toList());
        return placeOutputDTOS;
    }

    public Place readById(UUID id) {
        return placeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));

    }

    public PlaceOutputDTO update(UUID id, PlaceInputDTO placeInputDTO) {
        Place place = readById(id);
        placeInputDTO.setId(place.getId());
        placeInputDTO.setCreatedAt(place.getCreatedAt());
        place.onUpdate();
        placeInputDTO.setUpdatedAt(place.getUpdatedAt());
        BeanUtils.copyProperties(placeInputDTO, place);
        placeRepository.save(place);
        return placeMapper.toDTO(place);

    }

    public void delete(UUID id) {
        Place place = readById(id);
        placeRepository.delete(place);
    }

}
