package com.example.nuvenapi.service;

import com.example.nuvenapi.api.dto.PlaceInputDTO;
import com.example.nuvenapi.api.dto.PlaceOutputDTO;
import com.example.nuvenapi.api.dto.mapper.PlaceMapper;
import com.example.nuvenapi.model.Place;
import com.example.nuvenapi.repository.PlaceRepository;
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

    public PlaceOutputDTO update(UUID id, PlaceInputDTO placeInputDTO) {
        Place place = getById(id);
        placeInputDTO.setId(place.getId());
        placeInputDTO.setCreatedAt(place.getCreatedAt());
        place.onUpdate();
        BeanUtils.copyProperties(placeInputDTO, place);
        placeRepository.save(place);
        return placeMapper.toDTO(place);

    }

    public void delete(UUID id){
        Place place = getById(id);
        placeRepository.delete(place);
    }

    public Place getById(UUID id) {
        Place place = placeRepository.findById(id);
        return place;
    }
}
