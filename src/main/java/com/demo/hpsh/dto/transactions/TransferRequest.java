package com.demo.hpsh.dto.transactions;

import java.math.BigDecimal;

public record TransferRequest(Long fromAccountId, Long toAccountId, BigDecimal amount, String description) {

}
