package com.demo.hpsh.dto.transactions;

import java.math.BigDecimal;

public record WithdrawRequest(Long accountId, BigDecimal amountOut) {

}
