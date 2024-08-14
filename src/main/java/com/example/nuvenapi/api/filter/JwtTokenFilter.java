package com.example.nuvenapi.api.filter;

import com.example.nuvenapi.api.security.details.UserDetailsImpl;
import com.example.nuvenapi.domain.entity.Admin;
import com.example.nuvenapi.domain.repository.UserRepository;
import com.example.nuvenapi.domain.service.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;

    public JwtTokenFilter(JwtTokenService jwtTokenService, UserRepository userRepository) {
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (checkIfEndpointIsNotPublic(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = recoveryToken(request);

        if (token == null) {
            throw new RuntimeException("The token is missing");
        }
        String subject = null;
        try {
            subject = jwtTokenService.getSubjectFromToken(token);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao extrair subject");
        }
        Admin user = userRepository.findByUsername(subject).orElse(null);

        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }


    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return requestURI.equals("/auth") ||
                requestURI.startsWith("/swagger-ui") ||
                requestURI.startsWith("/api-docs");
    }
}
