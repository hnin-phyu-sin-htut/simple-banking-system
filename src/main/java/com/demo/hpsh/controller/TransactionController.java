package com.demo.hpsh.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.hpsh.dto.transactions.DepositRequest;
import com.demo.hpsh.dto.transactions.TransactionResponse;
import com.demo.hpsh.dto.transactions.TransferRequest;
import com.demo.hpsh.dto.transactions.WithdrawRequest;
import com.demo.hpsh.service.impl.TransactionServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
	
	private final TransactionServiceImpl transactionServiceImpl;
	
	@PostMapping("/deposit")
	public TransactionResponse deposit(@Valid @RequestBody DepositRequest depositRequest) {
		return transactionServiceImpl.deposit(depositRequest);
	}
	
	@PostMapping("/withdraw")
	public TransactionResponse withdraw(@Valid @RequestBody WithdrawRequest withdrawRequest) {
		return transactionServiceImpl.withdraw(withdrawRequest);
	}
	
	@PostMapping("/transfer")
	public void transfer(@Valid @RequestBody TransferRequest transferRequest) {
		transactionServiceImpl.transfer(transferRequest);
	}
	
	@GetMapping("/history/{id}")
	public List<TransactionResponse> getTransactionsHistory(@PathVariable("id") Long accountId) {
		return transactionServiceImpl.getTransactionsHistory(accountId);
	}

}
