package com.example.nuvenapi.domain.repository;

import com.example.nuvenapi.domain.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Admin, UUID> {

    Optional<Admin> findByUsername(String name);
}
