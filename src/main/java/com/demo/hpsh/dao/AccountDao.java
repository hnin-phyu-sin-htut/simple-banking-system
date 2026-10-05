package com.demo.hpsh.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.hpsh.entity.Account;

public interface AccountDao extends JpaRepository<Account, Long> {

	Optional<Account> findAccountByAccountNumber(Long accountNumber);

	List<Account> findAccountsByCustomerId(Long customerId);

}
