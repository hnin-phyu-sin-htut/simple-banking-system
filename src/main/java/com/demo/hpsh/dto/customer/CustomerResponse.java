package com.demo.hpsh.dto.customer;

import lombok.Builder;

@Builder
public record CustomerResponse(Long id, String name, String username, String email, String phoneNumber,
		String roleName) {

}
