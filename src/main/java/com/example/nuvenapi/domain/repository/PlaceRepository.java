package com.example.nuvenapi.domain.repository;


import com.example.nuvenapi.domain.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlaceRepository extends JpaRepository<Place, UUID>, PlaceRepositoryCustom  {

}
