package com.demo.hpsh.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.hpsh.dto.customer.CreateCustomerRequest;
import com.demo.hpsh.dto.customer.CustomerResponse;
import com.demo.hpsh.dto.customer.UpdateCustomerRequest;
import com.demo.hpsh.service.impl.CustomerServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
	
	private final CustomerServiceImpl customerServiceImpl;
	
	@PostMapping("/create-customer")
	public CustomerResponse createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
		return customerServiceImpl.createCustomer(createCustomerRequest);
	}
	
	@PutMapping("/update-customer/{id}")
	public CustomerResponse updateCustomer(@PathVariable Long id, @RequestBody UpdateCustomerRequest updateCustomerRequest) {
		return customerServiceImpl.updateCustomer(id, updateCustomerRequest);
	}
	
	@DeleteMapping("/delete-customer/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		customerServiceImpl.deleteCustomer(id);
	}
	
	@GetMapping("/{id}")
	public CustomerResponse getCustomerById(@PathVariable Long id) {
		return customerServiceImpl.getCustomerById(id);
	}
	
	@GetMapping("/get-all-customers")
	public List<CustomerResponse> getAllCustomers() {
		return customerServiceImpl.getAllCustomers();
	}

}
