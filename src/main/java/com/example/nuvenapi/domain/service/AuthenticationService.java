package com.example.nuvenapi.domain.service;

import com.example.nuvenapi.api.dto.UserInputDTO;
import com.example.nuvenapi.api.dto.UserOutputDTO;
import com.example.nuvenapi.domain.entity.Admin;
import com.example.nuvenapi.domain.exception.IncompatiblePasswordException;
import com.example.nuvenapi.domain.exception.UserNotFoundException;
import com.example.nuvenapi.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(JwtTokenService jwtTokenService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserOutputDTO authenticateUser(UserInputDTO userInputDTO) {

        Admin admin = userRepository.findByUsername(userInputDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException());


        if (!passwordEncoder.matches(userInputDTO.getPassword(), admin.getPassword())) {
            throw new IncompatiblePasswordException();
        }
        return UserOutputDTO.builder()
                .token(jwtTokenService.generateToken(admin.getUsername()))
                .build();
    }


}
