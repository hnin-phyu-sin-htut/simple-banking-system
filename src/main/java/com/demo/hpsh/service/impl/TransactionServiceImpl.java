package com.demo.hpsh.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.demo.hpsh.dao.AccountDao;
import com.demo.hpsh.dao.TransactionDao;
import com.demo.hpsh.dto.transactions.DepositRequest;
import com.demo.hpsh.dto.transactions.TransactionResponse;
import com.demo.hpsh.dto.transactions.TransferRequest;
import com.demo.hpsh.dto.transactions.WithdrawRequest;
import com.demo.hpsh.entity.Account;
import com.demo.hpsh.entity.Transaction;
import com.demo.hpsh.enums.TransactionType;
import com.demo.hpsh.service.TransactionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
	
	private final TransactionDao transactionDao;
	private final AccountDao accountDao;

	@Override
	@Transactional
	public TransactionResponse deposit(DepositRequest depositRequest) {
		Account account = accountDao.findById(depositRequest.accountId())
				.orElseThrow(() -> new RuntimeException("Accound not found."));
		account.setBalance(account.getBalance().add(depositRequest.amountIn()));
		Account savedAccount = accountDao.save(account);
		
		Transaction transaction = Transaction.builder()
				.account(savedAccount)
				.transactionType(TransactionType.DEPOSIT)
				.amount(depositRequest.amountIn())
				.build();
		Transaction savedTransaction = transactionDao.save(transaction);
		
		return mapToDto(savedTransaction);
	}

	@Override
	@Transactional
	public TransactionResponse withdraw(WithdrawRequest withdrawRequest) {
		Account account = accountDao.findById(withdrawRequest.accountId())
				.orElseThrow(() -> new RuntimeException("Account not found."));
		if (account.getBalance().compareTo(withdrawRequest.amountOut()) < 0) {
			throw new RuntimeException("Insufficient Balance!");
		}
		account.setBalance(account.getBalance().subtract(withdrawRequest.amountOut()));
		Account savedAccount = accountDao.save(account);
		
		Transaction transaction = Transaction.builder()
				.account(savedAccount)
				.transactionType(TransactionType.WITHDRAW)
				.amount(withdrawRequest.amountOut())
				.build();
		Transaction savedTransaction = transactionDao.save(transaction);
		return mapToDto(savedTransaction);
	}

	@Override
	@Transactional
	public void transfer(TransferRequest transferRequest) {
		Account fromAccountId = accountDao.findById(transferRequest.fromAccountId())
				.orElseThrow(() -> new RuntimeException("Sender Account not found."));
		Account toAccountId = accountDao.findById(transferRequest.toAccountId())
				.orElseThrow(() -> new RuntimeException("Reciever Account not found."));
		
		if (fromAccountId.getBalance().compareTo(transferRequest.amount()) < 0) {
			throw new RuntimeException("Insufficient Balance!");
		}
		fromAccountId.setBalance(fromAccountId.getBalance().subtract(transferRequest.amount()));
		toAccountId.setBalance(toAccountId.getBalance().add(transferRequest.amount()));
		accountDao.save(fromAccountId);
		accountDao.save(toAccountId);
		
		Transaction transactionOut = Transaction.builder()
				.account(fromAccountId)
				.transactionType(TransactionType.TRANSFER_OUT)
				.amount(transferRequest.amount())
				.description(transferRequest.description())
				.build();
		
		Transaction transactionIn = Transaction.builder()
				.account(toAccountId)
				.transactionType(TransactionType.TRANSFER_IN)
				.amount(transferRequest.amount())
				.description(transferRequest.description())
				.build();
		
		transactionDao.save(transactionOut);
		transactionDao.save(transactionIn);
	}

	@Override
	public List<TransactionResponse> getTransactionsHistory(Long accountId) {
		return transactionDao.findByAccountIdOrderByCreatedAtDesc(accountId)
				.stream().map(this::mapToDto).toList();
	}
	
	public TransactionResponse mapToDto(Transaction transaction) {
		return TransactionResponse.builder()
				.transactionId(transaction.getId())
				.transactionType(transaction.getTransactionType())
				.amount(transaction.getAmount())
				.build();
	}
	
	public Transaction mapToEntity(TransactionResponse dto) {
		Transaction transaction = new Transaction();
		BeanUtils.copyProperties(dto, transaction);
		return transaction;
	}

}
