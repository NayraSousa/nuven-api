package com.example.nuvenapi.repository;

import com.example.nuvenapi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
