package com.demo.hpsh.dto.customer;

public record CreateCustomerRequest(
		String name, 
		String email, 
		String phoneNumber
		) {
}
