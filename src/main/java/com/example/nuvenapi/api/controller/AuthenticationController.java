package com.example.nuvenapi.api.controller;

import com.example.nuvenapi.api.dto.UserInputDTO;
import com.example.nuvenapi.api.dto.UserOutputDTO;
import com.example.nuvenapi.domain.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<UserOutputDTO> authenticateUser(@RequestBody @Valid UserInputDTO userInputDTO) {
        return ResponseEntity.ok(authenticationService.authenticateUser(userInputDTO));
    }
}
