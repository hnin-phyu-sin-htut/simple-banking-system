package com.demo.hpsh.dto.transactions;

import java.math.BigDecimal;

import com.demo.hpsh.enums.TransactionType;

import lombok.Builder;

@Builder
public record TransactionResponse(Long transactionId, TransactionType transactionType, BigDecimal amount) {

}
