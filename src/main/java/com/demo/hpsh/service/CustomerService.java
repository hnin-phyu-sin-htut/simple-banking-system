package com.demo.hpsh.service;

import java.util.List;

import com.demo.hpsh.dto.customer.CreateCustomerRequest;
import com.demo.hpsh.dto.customer.CustomerResponse;
import com.demo.hpsh.dto.customer.UpdateCustomerRequest;

public interface CustomerService {

	CustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);

	CustomerResponse updateCustomer(Long id, UpdateCustomerRequest updateCustomerRequest);

	void deleteCustomer(Long id);

	CustomerResponse getCustomerById(Long id);

	List<CustomerResponse> getAllCustomers();

}
