package com.demo.hpsh.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.demo.hpsh.dao.AccountDao;
import com.demo.hpsh.dao.CustomerDao;
import com.demo.hpsh.dto.account.AccountResponse;
import com.demo.hpsh.dto.account.CreateAccountRequest;
import com.demo.hpsh.entity.Account;
import com.demo.hpsh.entity.Customer;
import com.demo.hpsh.service.AccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
	
	private final AccountDao accountDao;
	private final CustomerDao customerDao;

	@Override
	public AccountResponse createAccount(CreateAccountRequest createAccountRequest) {
		Customer customer = customerDao.findById(createAccountRequest.customerId())
				.orElseThrow(() -> new RuntimeException("Customer not found."));
		
		Account account = Account.builder()
				.customer(customer)
				.build();
		Account savedAccount = accountDao.save(account);
		return mapToDto(savedAccount);
	}

	@Override
	public AccountResponse getAccountById(Long id) {
		Account account = accountDao.findById(id)
				.orElseThrow(() -> new RuntimeException("Account not found with id : " + id));
		return mapToDto(account);
	}

	@Override
	public AccountResponse getAccountByAccountNumber(String accountNumber) {
		Account account = accountDao.findAccountByAccountNumber(accountNumber)
				.orElseThrow(() -> new RuntimeException("Account not found."));
		return mapToDto(account);
	}

	@Override
	public List<AccountResponse> getAllAccounts() {
		return accountDao.findAll().stream().map(this::mapToDto).toList();
	}

	@Override
	public List<AccountResponse> getAccountsByCustomerId(Long customerId) {
		return accountDao.findAccountsByCustomerId(customerId)
				.stream()
				.map(this::mapToDto)
				.toList();
	}
	
	public AccountResponse mapToDto(Account account) {
		return AccountResponse.builder()
				.customerId(account.getCustomer().getId())
				.build();
	}
	
	public Account mapToEntity(AccountResponse dto) {
		Account account = new Account();
		BeanUtils.copyProperties(dto, account);
		return account;
	}

}
