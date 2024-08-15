package com.example.nuvenapi.domain.service;

import com.example.nuvenapi.api.dto.AddressInputDTO;
import com.example.nuvenapi.api.dto.AddressOutputDTO;
import com.example.nuvenapi.api.dto.PlaceInputDTO;
import com.example.nuvenapi.api.dto.PlaceOutputDTO;
import com.example.nuvenapi.api.dto.mapper.PlaceMapper;
import com.example.nuvenapi.domain.entity.Address;
import com.example.nuvenapi.domain.entity.Place;
import com.example.nuvenapi.domain.repository.PlaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlaceServiceTest {

    @InjectMocks
    private PlaceService placeService;

    @MockBean
    private PlaceMapper placeMapper;

    @MockBean
    private PlaceRepository placeRepository;

    Place place;

    @BeforeEach
    void setUp() {
        placeMapper = mock(PlaceMapper.class);
        placeRepository = mock(PlaceRepository.class);
        placeService = new PlaceService(placeRepository, placeMapper);
        place = new Place();

        place = Place.builder()
                .name("name")
                .address(Address.builder()
                        .street("street")
                        .number("number")
                        .district("district")
                        .city("city")
                        .state("state").build())
                .description("description")
                .build();
    }

    @Test
    void createCaseSuccess() {
        PlaceInputDTO placeInputDTO = PlaceInputDTO.builder()
                .name("name")
                .address(AddressInputDTO.builder()
                        .street("street")
                        .number("number")
                        .district("district")
                        .city("city")
                        .state("state").build())
                .description("description")
                .build();

        PlaceOutputDTO placeOutputDTO = PlaceOutputDTO.builder()
                .id(UUID.randomUUID())
                .name("name")
                .address(AddressOutputDTO.builder()
                        .street("street")
                        .number("number")
                        .district("district")
                        .city("city")
                        .state("state").build())
                .description("description")
                .build();

        when(placeMapper.toDTO(place)).thenReturn(placeOutputDTO);
        when(placeMapper.toEntity(placeInputDTO)).thenReturn(place);

        when(placeRepository.save(ArgumentMatchers.any())).thenReturn(place);

        PlaceOutputDTO placeOutput = placeService.create(placeInputDTO);


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

    @Test
    void readCaseSuccess() {

        PlaceOutputDTO placeOutputDTO = PlaceOutputDTO.builder()
                .name("name")
                .address(AddressOutputDTO.builder()
                        .street("street")
                        .number("number")
                        .district("district")
                        .city("city")
                        .state("state").build())
                .description("description")
                .build();

        given(placeRepository.readAllSorted()).willReturn(List.of(place));

        when(placeMapper.toDTO(place)).thenReturn(placeOutputDTO);

        List<PlaceOutputDTO> placeOutputDTOList = placeService.readAllSorted();
        assertEquals(1, placeOutputDTOList.size());

    }

    @Test
    void updateCaseSuccess() {

        UUID id = UUID.randomUUID();

        PlaceInputDTO placeInputDTO = PlaceInputDTO.builder()
                .name("name")
                .address(AddressInputDTO.builder()
                        .street("street")
                        .number("number")
                        .district("district")
                        .city("city")
                        .state("state").build())
                .description("description")
                .build();

        PlaceOutputDTO placeOutputDTO = PlaceOutputDTO.builder()
                .id(UUID.randomUUID())
                .name("name")
                .address(AddressOutputDTO.builder()
                        .street("street")
                        .number("number")
                        .district("district")
                        .city("city")
                        .state("state").build())
                .description("description")
                .build();

        Place place1 = Place.builder()
                .id(id)
                .name("name")
                .address(Address.builder()
                        .street("street")
                        .number("number")
                        .district("district")
                        .city("city")
                        .state("state").build())
                .description("description")
                .build();

        given(placeRepository.findById(id)).willReturn(Optional.ofNullable(place1));

        given(placeRepository.save(place1)).willReturn(place1);

        PlaceOutputDTO placeOutputDTO1 = placeService.update(id, placeInputDTO);

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

    @Test
    void deleteCaseSuccess() {
        UUID id = UUID.randomUUID();

        Place place = Place.builder()
                .id(id)
                .name("name")
                .address(Address.builder()
                        .street("street")
                        .number("number")
                        .district("district")
                        .city("city")
                        .state("state").build())
                .description("description")
                .build();

        given(placeRepository.findById(id)).willReturn(Optional.ofNullable(place));
        willDoNothing().given(placeRepository).delete(place);

        placeService.delete(id);

        verify(placeRepository, times(1)).delete(place);


    }

}
