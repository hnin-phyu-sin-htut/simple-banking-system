package com.demo.hpsh.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.hpsh.entity.Transaction;

public interface TransactionDao extends JpaRepository<Transaction, Long> {
	
	List<Transaction> findByAccountIdOrderByCreatedAtDesc(Long accountId);

}
