package com.example.nuvenapi.repository;


import com.example.nuvenapi.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long>  {
}
