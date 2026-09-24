package com.demo.hpsh.dto.account;

import lombok.Builder;

@Builder
public record AccountResponse(
		Long customerId
		) {

}
