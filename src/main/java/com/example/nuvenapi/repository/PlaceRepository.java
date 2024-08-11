package com.example.nuvenapi.repository;


import com.example.nuvenapi.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface PlaceRepository extends JpaRepository<Place, Long>  {
    Place findById(UUID id);
}
