package com.demo.hpsh.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.demo.hpsh.dao.CustomerDao;
import com.demo.hpsh.dto.customer.CreateCustomerRequest;
import com.demo.hpsh.dto.customer.CustomerResponse;
import com.demo.hpsh.dto.customer.UpdateCustomerRequest;
import com.demo.hpsh.entity.Customer;
import com.demo.hpsh.enums.CustomerStatus;
import com.demo.hpsh.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final CustomerDao customerDao;

	@Override
	public CustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {
		Customer customer = Customer.builder()
				.name(createCustomerRequest.name())
				.email(createCustomerRequest.email())
				.phoneNumber(createCustomerRequest.phoneNumber())
				.build();
		Customer savedCustomer = customerDao.save(customer);
		return mapToDto(savedCustomer);
	}

	@Override
	public CustomerResponse updateCustomer(Long id, UpdateCustomerRequest updateCustomerRequest) {
		Customer customer = customerDao.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer not found to update."));
		customer.setName(updateCustomerRequest.name());
		customer.setEmail(updateCustomerRequest.email());
		customer.setPhoneNumber(updateCustomerRequest.phoneNumber());
		
		Customer savedCustomer = customerDao.save(customer);
		return mapToDto(savedCustomer);
	}

	@Override
	public void deleteCustomer(Long id) {
		Customer customer = customerDao.findById(id).orElseThrow(() -> new RuntimeException("Customer not found."));
		customerDao.delete(customer);
	}

	@Override
	public CustomerResponse getCustomerById(Long id) {
		Customer customer = customerDao.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
		return mapToDto(customer);
	}

	@Override
	public List<CustomerResponse> getAllCustomers() {
		return customerDao.findAll().stream().map(this::mapToDto).toList();
	}

	public CustomerResponse mapToDto(Customer customer) {
		return CustomerResponse.builder().name(customer.getName()).email(customer.getEmail())
				.phoneNumber(customer.getPhoneNumber()).build();
	}

	public Customer mapToEntity(CustomerResponse dto) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(dto, customer);
		return customer;
	}

}
