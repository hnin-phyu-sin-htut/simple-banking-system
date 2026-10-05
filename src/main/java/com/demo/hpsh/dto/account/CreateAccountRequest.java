package com.demo.hpsh.dto.account;

import jakarta.validation.constraints.NotNull;

public record CreateAccountRequest(@NotNull(message = "Customer ID is required.") Long customerId) {

}
