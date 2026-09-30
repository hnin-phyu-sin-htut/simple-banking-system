package com.demo.hpsh.dto.transactions;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DepositRequest(
			@NotNull(message = "Account ID is required.")
			Long accountId, 
			
			@NotNull(message = "Amount is required.")
			@Positive(message = "Amount must be greater than zero.")
			BigDecimal amountIn
		) {

}
