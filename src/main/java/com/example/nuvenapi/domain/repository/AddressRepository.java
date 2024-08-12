package com.example.nuvenapi.domain.model.repository;

import com.example.nuvenapi.domain.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
