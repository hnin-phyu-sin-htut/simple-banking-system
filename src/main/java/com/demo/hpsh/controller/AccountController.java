package com.demo.hpsh.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.hpsh.dto.account.AccountResponse;
import com.demo.hpsh.dto.account.CreateAccountRequest;
import com.demo.hpsh.service.impl.AccountServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
	
	private final AccountServiceImpl accountServiceImpl;
	
	@PostMapping("/create-account")
	public AccountResponse createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
		return accountServiceImpl.createAccount(createAccountRequest);
	}
	
	@GetMapping("/{id}")
	public AccountResponse getAccountById(@PathVariable Long id) {
		return accountServiceImpl.getAccountById(id);
	}
	
	@GetMapping("/get-account-by-account-number")
	public AccountResponse getAccountByAccountNumber(@RequestParam String accountNumber) {
		return accountServiceImpl.getAccountByAccountNumber(accountNumber);
	}
	
	@GetMapping("/get-all-accounts")
	public List<AccountResponse> getAllAccounts() {
		return accountServiceImpl.getAllAccounts();
	}
	
	@GetMapping("/get-accounts-by-customer-id/{customerId}")
	public List<AccountResponse> getAccountsByCustomerId(@PathVariable Long customerId) {
		return accountServiceImpl.getAccountsByCustomerId(customerId);
	}

}
