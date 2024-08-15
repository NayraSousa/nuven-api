package com.example.nuvenapi.api.controller;

import com.example.nuvenapi.api.dto.AddressOutputDTO;
import com.example.nuvenapi.api.dto.PlaceInputDTO;
import com.example.nuvenapi.api.dto.PlaceOutputDTO;
import com.example.nuvenapi.domain.entity.Address;
import com.example.nuvenapi.domain.entity.Place;
import com.example.nuvenapi.domain.service.JwtTokenService;
import com.example.nuvenapi.domain.service.PlaceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class PlaceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    PlaceService placeService;

    String token;

    @BeforeEach
    public void setup() {
        token = new JwtTokenService().generateToken("admin");
    }

    @Test
    public void returnCreatedWhenCreatePlace() throws Exception {
        PlaceOutputDTO place = PlaceOutputDTO.builder()
                .name("name")
                .address(AddressOutputDTO.builder()
                        .street("street")
                        .number("number")
                        .district("district")
                        .state("state")
                        .city("city").build())
                .description("description")
                .build();
        given(placeService.create(any(PlaceInputDTO.class))).willReturn(place);

        ResultActions response = mockMvc.perform(post("/place/create")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .content(mapper.writeValueAsString(place)));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.description").value("description")).andReturn();
    }

    @Test
    public void returnBadRequestWhenNameFieldIsNull() throws Exception {

        PlaceOutputDTO place = PlaceOutputDTO.builder()
                .address(AddressOutputDTO.builder()
                        .street("street")
                        .number("number")
                        .district("district")
                        .state("state")
                        .city("city").build())
                .description("description")
                .build();

        given(placeService.create(any(PlaceInputDTO.class))).willReturn(place);

        mockMvc.perform(post("/place/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(mapper.writeValueAsString(place)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("VALIDATION_ERROR"))
                .andExpect(jsonPath("$.detail").value("Field 'name' is null"))
                .andReturn();

    }

    @Test
    public void returnBadRequestWhenAddressFielIsNull() throws Exception {
        PlaceOutputDTO place = PlaceOutputDTO.builder()
                .name("name")
                .description("description")
                .build();

        given(placeService.create(any(PlaceInputDTO.class))).willReturn(place);

        mockMvc.perform(post("/place/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(mapper.writeValueAsString(place)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("VALIDATION_ERROR"))
                .andExpect(jsonPath("$.detail").value("Field 'address' is null"))
                .andReturn();

    }

    @Test
    public void returnBadRequestWhenDescriptionFieldIsNull() throws Exception {
        PlaceOutputDTO place = PlaceOutputDTO.builder()
                .name("name")
                .address(AddressOutputDTO.builder()
                        .street("street")
                        .number("number")
                        .district("district")
                        .state("state")
                        .city("city").build())
                .build();

        given(placeService.create(any(PlaceInputDTO.class))).willReturn(place);

        mockMvc.perform(post("/place/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(mapper.writeValueAsString(place)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("VALIDATION_ERROR"))
                .andExpect(jsonPath("$.detail").value("Field 'description' is null"))
                .andReturn();
    }

    @Test
    public void returnOkWhenReadAll() throws Exception {

        PlaceOutputDTO place = PlaceOutputDTO.builder()
                .name("name")
                .address(AddressOutputDTO.builder()
                        .street("street")
                        .number("number")
                        .district("district")
                        .state("state")
                        .city("city").build())
                .description("description")
                .build();

        List<PlaceOutputDTO> placeList = new ArrayList<>();
        placeList.add(place);
        placeList.add(PlaceOutputDTO.builder()
                .name("nametwo")
                .address(AddressOutputDTO.builder()
                        .street("streettwo")
                        .number("numbertwo")
                        .district("districttwo")
                        .state("statetwo")
                        .city("citytwo").build())
                .description("descriptiontwo")
                .build());

        given(placeService.readAllSorted()).willReturn(placeList);

        ResultActions response = mockMvc.perform(get("/place/read-all")
                .header("Authorization", "Bearer " + token));

        response.andExpect(status().isOk());
    }

    @Test
    public void returnOkWhenReadByName() throws Exception {

        List<PlaceOutputDTO> placeList = new ArrayList<>();
        placeList.add(PlaceOutputDTO.builder()
                .name("name")
                .address(AddressOutputDTO.builder()
                        .street("street")
                        .number("number")
                        .district("district")
                        .state("state")
                        .city("city").build())
                .description("description")
                .build());

        given(placeService.readByName("name")).willReturn(placeList);

        ResultActions response = mockMvc.perform(get("/place/read-by-name")
                .header("Authorization", "Bearer " + token)
                .param("name", "name"));

        response.andExpect(status().isOk());
    }

    @Test
    public void returnOkWhenReadById() throws Exception {
        UUID id = UUID.randomUUID();
        Place place = Place.builder()
                .id(id)
                .name("name")
                .address(Address.builder()
                        .street("street")
                        .number("number")
                        .district("district")
                        .state("state")
                        .city("city").build())
                .description("description")
                .build();

        given(placeService.readById(id)).willReturn(place);

        ResultActions response = mockMvc.perform(get("/place/read-by-id/" + id)
                .header("Authorization", "Bearer " + token));

        response.andExpect(status().isOk());
    }

    @Test
    public void returnNotFoundWhenReadByID() throws Exception {
        UUID id = UUID.randomUUID();
        Place place = Place.builder()
                .name("name")
                .address(Address.builder()
                        .street("street")
                        .number("number")
                        .district("district")
                        .state("state")
                        .city("city").build())
                .description("description")
                .build();

        given(placeService.readById(id)).willReturn(place);

        ResultActions response = mockMvc.perform(get("/place/read-by-id/")
                .header("Authorization", "Bearer " + token));

        response.andExpect(status().isNotFound());

    }

    @Test
    public void returnOkWhenUpdate() throws Exception {
        UUID id = UUID.randomUUID();
        PlaceOutputDTO place = PlaceOutputDTO.builder()
                .name("name")
                .address(AddressOutputDTO.builder()
                        .street("street")
                        .number("number")
                        .district("district")
                        .state("state")
                        .city("city").build())
                .description("description")
                .build();

        given(placeService.update(eq(id), any(PlaceInputDTO.class))).willReturn(place);

        ResultActions response = mockMvc.perform(put("/place/update/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .content(mapper.writeValueAsString(place)));

        response.andExpect(status().isOk());
    }

    @Test
    public void returnBadRequestWhenUpdate() throws Exception {

        UUID id = UUID.randomUUID();
        PlaceOutputDTO place = PlaceOutputDTO.builder()
                .name("name")
                .address(AddressOutputDTO.builder()
                        .street("street")
                        .number("number")
                        .district("district")
                        .state("state")
                        .city("city").build())
                .description("description")
                .build();

        given(placeService.update(eq(id), any(PlaceInputDTO.class))).willReturn(place);

        ResultActions response = mockMvc.perform(put("/place/update/")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .content(mapper.writeValueAsString(place)));

        response.andExpect(status().isNotFound());
    }

    @Test
    public void returnNoContentWhenDelete() throws Exception {

        UUID id = UUID.randomUUID();
        PlaceOutputDTO place = PlaceOutputDTO.builder()
                .id(id)
                .name("name")
                .address(AddressOutputDTO.builder()
                        .street("street")
                        .number("number")
                        .district("district")
                        .state("state")
                        .city("city").build())
                .description("description")
                .build();

        mockMvc.perform(delete("/place/delete/" + id)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isNoContent());

    }

}