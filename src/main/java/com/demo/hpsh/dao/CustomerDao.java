package com.demo.hpsh.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.hpsh.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

	Optional<Customer> findByUsername(String username);

}
