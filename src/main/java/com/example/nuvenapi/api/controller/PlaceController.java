package com.example.nuvenapi.api.controller;

import com.example.nuvenapi.api.dto.PlaceInputDTO;
import com.example.nuvenapi.api.dto.PlaceOutputDTO;
import com.example.nuvenapi.domain.entity.Place;
import com.example.nuvenapi.domain.service.PlaceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/place")
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/create")
    public PlaceOutputDTO create(@RequestBody @Valid PlaceInputDTO placeInputDTO) {
        return placeService.create(placeInputDTO);
    }

    @GetMapping("/read-all")
    public List<PlaceOutputDTO> readAll(){
        return placeService.readAll();
    }

    @PutMapping("/update/{id}")
    public PlaceOutputDTO update(@PathVariable UUID id, @RequestBody @Valid PlaceInputDTO placeInputDTO){
        return placeService.update(id, placeInputDTO);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable UUID id){
        placeService.delete(id);
    }

    @GetMapping("/{id}")
    public Place getById(@PathVariable UUID id){
        return placeService.getById(id);
    }
}
