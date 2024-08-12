package com.example.nuvenapi.domain.model.repository;


import com.example.nuvenapi.domain.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface PlaceRepository extends JpaRepository<Place, Long>  {
    Place findById(UUID id);
}
