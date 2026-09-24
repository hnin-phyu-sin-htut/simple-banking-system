package com.demo.hpsh.service;

import java.util.List;

import com.demo.hpsh.dto.account.AccountResponse;
import com.demo.hpsh.dto.account.CreateAccountRequest;

public interface AccountService {
	
	AccountResponse createAccount(CreateAccountRequest createAccountRequest);
	
	AccountResponse getAccountById(Long id);
	
	AccountResponse getAccountByAccountNumber(String accountNumber);
	
	List<AccountResponse> getAllAccounts();
	
	List<AccountResponse> getAccountsByCustomerId(Long customerId);

}
