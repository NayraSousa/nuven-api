package com.example.nuvenapi.api.controller;

import com.example.nuvenapi.api.dto.AddressOutputDTO;
import com.example.nuvenapi.domain.service.AddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/read-all")
    public List<AddressOutputDTO> readAll() {
        return addressService.readAll();

    }
}
