package com.demo.hpsh.dto.transactions;

import java.time.LocalDateTime;

import com.demo.hpsh.enums.TransactionType;

public record TransactionResponse(
		Long accountId, 
		TransactionType transactionType, 
		double amount, 
		String description, 
		LocalDateTime createdAt
		) {

}
