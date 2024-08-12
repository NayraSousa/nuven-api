package com.example.nuvenapi.domain.repository;

import com.example.nuvenapi.domain.entity.Place;

import java.util.List;

public interface PlaceRepositoryCustom {

    List<Place> getByName(String name);

    List<Place> readAllSorted();

}
