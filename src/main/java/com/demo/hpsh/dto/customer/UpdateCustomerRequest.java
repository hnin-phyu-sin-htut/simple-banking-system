package com.demo.hpsh.dto.customer;

public record UpdateCustomerRequest(String name, String username, String password, String email, String phoneNumber,
		String roleName) {

}
