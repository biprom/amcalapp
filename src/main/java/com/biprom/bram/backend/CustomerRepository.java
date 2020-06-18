package com.biprom.bram.backend;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biprom.bram.backend.data.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
