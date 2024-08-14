package com.example.nuvenapi.domain.seeder;

import com.example.nuvenapi.domain.entity.Admin;
import com.example.nuvenapi.domain.repository.UserRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SeederUser {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SeederUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedSave();
    }

    void seedSave() {
        if (userRepository.count() >= 1) {
            return;
        }

        Admin admin = Admin.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .build();
        userRepository.save(admin);
    }
}
