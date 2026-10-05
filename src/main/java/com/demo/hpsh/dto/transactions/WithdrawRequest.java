package com.demo.hpsh.dto.transactions;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record WithdrawRequest(
		@NotNull(message = "Account Number is required.") 
		Long accountNumber,

		@NotNull(message = "Amount is required.") 
		@Positive(message = "Amount must be greater than zero.") 
		BigDecimal amountOut) {

}
