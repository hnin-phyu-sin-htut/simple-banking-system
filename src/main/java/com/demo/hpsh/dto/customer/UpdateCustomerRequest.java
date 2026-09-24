package com.demo.hpsh.dto.customer;

public record UpdateCustomerRequest(
		String name, 
		String email, 
		String phoneNumber
		) {

}
