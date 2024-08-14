package com.example.nuvenapi.domain.service;

import com.example.nuvenapi.api.dto.PlaceInputDTO;
import com.example.nuvenapi.api.dto.PlaceOutputDTO;
import com.example.nuvenapi.api.dto.mapper.PlaceMapper;
import com.example.nuvenapi.domain.entity.Address;
import com.example.nuvenapi.domain.entity.Place;
import com.example.nuvenapi.domain.repository.PlaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlaceServiceTest {

    private PlaceService placeService;

    @Mock
    private PlaceMapper placeMapper;

    @MockBean
    private PlaceRepository placeRepository;

    @BeforeEach
    void setUp() {
        placeRepository = mock(PlaceRepository.class);
        placeService = new PlaceService(placeRepository, placeMapper);
    }

    @Test
    @DisplayName("Sucessfully")
    void createCaseSucess() {
        UUID id = UUID.randomUUID();
        Place place = Place.builder()
                .id(id)
                .name("name")
                .address(Address.builder().id(UUID .randomUUID())
                        .street("stree")
                        .number("number")
                        .district("district")
                        .city("city")
                        .state("state").build())
                .description("description")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        PlaceInputDTO placeInputDTO = PlaceInputDTO.builder()
                        .name("name")
                                .address(Address.builder()
                                        .street("street")
                                        .number("number")
                                        .district("district")
                                        .city("city")
                                        .state("state").build())
                .description("description")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(placeRepository.save(ArgumentMatchers.any())).thenReturn(place);

        PlaceOutputDTO placeOutputDTO = placeService.create(placeInputDTO);


        verify(placeRepository, times(1)).save(ArgumentMatchers.any());
        assertNotNull(placeOutputDTO.getId());
        assertEquals(placeInputDTO.getName(), placeOutputDTO.getName());
        assertEquals(placeInputDTO.getAddress().getStreet(), placeOutputDTO.getAddress().getStreet());
        assertEquals(placeInputDTO.getAddress().getId(), placeOutputDTO.getAddress().getId());
        assertEquals(placeInputDTO.getAddress().getNumber(), placeOutputDTO.getAddress().getNumber());
        assertEquals(placeInputDTO.getAddress().getCity(), placeOutputDTO.getAddress().getCity());
        assertEquals(placeInputDTO.getAddress().getState(), placeOutputDTO.getAddress().getState());
        assertEquals(placeInputDTO.getAddress().getDistrict(), placeOutputDTO.getAddress().getDistrict());
        assertEquals(placeInputDTO.getDescription(), placeOutputDTO.getDescription());
        assertEquals(placeInputDTO.getCreatedAt(), placeOutputDTO.getCreatedAt());

    }

}