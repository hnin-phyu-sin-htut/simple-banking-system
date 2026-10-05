package com.demo.hpsh.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.demo.hpsh.enums.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;

	private BigDecimal amount;
	private BigDecimal amountIn;
	private BigDecimal amountOut;

	@Column(columnDefinition = "TEXT")
	private String description;

	private LocalDateTime createdAt;

	@ManyToOne
	private Account account;

	@PrePersist
	public void prePersist() {

		if (transactionType == TransactionType.DEPOSIT || transactionType == TransactionType.TRANSFER_IN) {
			this.amountIn = amount;
			this.amountOut = BigDecimal.ZERO;
		} else if (transactionType == TransactionType.WITHDRAW || transactionType == TransactionType.TRANSFER_OUT) {
			this.amountOut = amount;
			this.amountIn = BigDecimal.ZERO;
		}
		if (transactionType == TransactionType.TRANSFER_IN) {
			transactionType = TransactionType.TRANSFER_IN;
		}
		if (transactionType == TransactionType.TRANSFER_OUT) {
			transactionType = TransactionType.TRANSFER_OUT;
		}

		this.createdAt = LocalDateTime.now();
	}

}
