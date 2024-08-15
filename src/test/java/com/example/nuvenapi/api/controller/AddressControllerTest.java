package com.example.nuvenapi.api.controller;

import com.example.nuvenapi.api.dto.AddressOutputDTO;
import com.example.nuvenapi.domain.service.AddressService;
import com.example.nuvenapi.domain.service.JwtTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    AddressOutputDTO address;
    String token;

    @BeforeEach
    public void setup() {
        address = AddressOutputDTO.builder()
                .id(UUID.randomUUID())
                .street("street")
                .number("number")
                .district("district")
                .city("city")
                .state("state")
                .build();

        token = new JwtTokenService().generateToken("admin");
    }

    @Test
    public void returnOkWhenReadAll() throws Exception {

        List<AddressOutputDTO> addressList = new ArrayList<>();
        addressList.add(address);
        addressList.add(AddressOutputDTO.builder()
                .id(UUID.randomUUID())
                .street("streetTwo")
                .number("numberTwo")
                .district("districtTwo")
                .city("cityTwo")
                .state("stateTwo")
                .build());

        given(addressService.readAll()).willReturn(addressList);

        ResultActions response = mockMvc.perform(get("/address/read-all")
                .header("Authorization", "Bearer " + token));

        response.andExpect(status().isOk());


    }


}