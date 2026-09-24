package com.demo.hpsh.dto.customer;

import lombok.Builder;

@Builder
public record CustomerResponse(
		String name, 
		String email, 
		String phoneNumber
		) {

}
