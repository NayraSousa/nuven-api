package com.example.nuvenapi.domain.repository;

import com.example.nuvenapi.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
