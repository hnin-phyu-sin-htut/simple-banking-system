package com.demo.hpsh.dto.transactions;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransferRequest(
		@NotNull(message = "Sender account is required.") 
		Long fromAccountNumber,

		@NotNull(message = "Receiver account is required.") 
		Long toAccountNumber,

		@NotNull(message = "Amount is required.") 
		@Positive(message = "Amount must be greater than zero.") 
		BigDecimal amount,

		String description) {

}
