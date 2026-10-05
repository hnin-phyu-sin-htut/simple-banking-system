package com.demo.hpsh.dto;

public record RegisterRequest(String name, String username, String password, String email, String phoneNumber,
		String roleName) {

}
