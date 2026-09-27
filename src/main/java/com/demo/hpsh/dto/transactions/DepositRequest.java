package com.demo.hpsh.dto.transactions;

import java.math.BigDecimal;

public record DepositRequest(Long accountId, BigDecimal amountIn) {

}
