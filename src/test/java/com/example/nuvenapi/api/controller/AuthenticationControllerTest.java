package com.example.nuvenapi.api.controller;

import com.example.nuvenapi.api.dto.UserInputDTO;
import com.example.nuvenapi.domain.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    ObjectMapper objectMapper;

    String token;

    @BeforeEach
    void setUp() {
        token = "Bearer " + authenticationService.authenticateUser(new UserInputDTO("admin", "admin")).getToken();
    }

    @Test
    void shouldReturnOKWhenAuthenticateWithValidCredentials() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth")
                        .header("Content-Type", "application/json")
                        .content(objectMapper.writeValueAsString(new UserInputDTO("admin", "admin"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }


    @Test
    void shouldReturnUnauthorizedWhenAuthenticateWithInvalidCredentials() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth")
                        .header("Content-Type", "application/json")
                        .content(objectMapper.writeValueAsString(new UserInputDTO("admin", "adminn"))))
                .andExpect(status().isBadRequest());
    }


}