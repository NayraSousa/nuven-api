package com.example.nuvenapi.service;

import com.example.nuvenapi.api.dto.AddressOutputDTO;
import com.example.nuvenapi.api.dto.mapper.AddressMapper;
import com.example.nuvenapi.domain.model.Address;
import com.example.nuvenapi.domain.model.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    public List<AddressOutputDTO> readAll() {
        List<Address> addresses = addressRepository.findAll();
        List<AddressOutputDTO> addressesOutputDTOS = new ArrayList<>();
        addressesOutputDTOS = addresses
                .stream()
                .map(addressMapper::toDTO)
                .collect(Collectors.toList());
        return addressesOutputDTOS;
    }

}
