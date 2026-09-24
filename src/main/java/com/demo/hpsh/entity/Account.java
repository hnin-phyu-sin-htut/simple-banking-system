package com.demo.hpsh.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.demo.hpsh.enums.AccountStatus;
import com.demo.hpsh.enums.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private Long accountNumber;

	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	private BigDecimal balance;

	@Enumerated(EnumType.STRING)
	private AccountStatus accountStatus;

	private LocalDateTime createdAt;

	@ManyToOne
	private Customer customer;

	@Builder.Default
	@OneToMany(mappedBy = "account")
	private List<Transaction> transactions = new ArrayList<>();

	public void addTransaction(Account account) {
		Transaction transaction = new Transaction();
		transactions.add(transaction);
	}

	@PrePersist
	public void prePersist() {
		LocalDateTime createdAt = LocalDateTime.now();
		this.createdAt = createdAt;
		this.accountNumber = (long) (Math.random() * 9000000000L) + 1000000000L;
		this.accountStatus = AccountStatus.ACTIVE;
		this.accountType = AccountType.NORMAL;
		this.balance = BigDecimal.ZERO;
	}

}
