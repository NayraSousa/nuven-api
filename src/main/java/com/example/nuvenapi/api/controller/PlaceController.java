package com.example.nuvenapi.api.controller;

import com.example.nuvenapi.api.dto.PlaceInputDTO;
import com.example.nuvenapi.api.dto.PlaceOutputDTO;
import com.example.nuvenapi.model.Place;
import com.example.nuvenapi.service.PlaceService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/place")
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/create")
    public PlaceOutputDTO create(@RequestBody PlaceInputDTO placeInputDTO) {
        return placeService.create(placeInputDTO);
    }

    @GetMapping("/read-all")
    public List<PlaceOutputDTO> readAll(){
        return placeService.readAll();
    }

    @PutMapping("/{id}")
    public PlaceOutputDTO update(@PathVariable UUID id, @RequestBody PlaceInputDTO placeInputDTO){
        return placeService.update(id, placeInputDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable UUID id){
        placeService.delete(id);
    }

    @GetMapping("/{id}")
    public Place getByUUID(@PathVariable UUID id){
        return placeService.getById(id);
    }
}
