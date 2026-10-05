package com.demo.hpsh.service;

import java.util.List;

import com.demo.hpsh.dto.transactions.DepositRequest;
import com.demo.hpsh.dto.transactions.TransactionResponse;
import com.demo.hpsh.dto.transactions.TransferRequest;
import com.demo.hpsh.dto.transactions.WithdrawRequest;

public interface TransactionService {

	TransactionResponse deposit(DepositRequest depositRequest);

	TransactionResponse withdraw(WithdrawRequest withdrawRequest);

	void transfer(TransferRequest transferRequest);

	List<TransactionResponse> getTransactionsHistory(Long accountId);

}
