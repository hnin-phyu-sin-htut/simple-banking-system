package com.demo.hpsh.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.demo.hpsh.enums.CustomerStatus;

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
import jakarta.persistence.PreUpdate;
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
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String username;
	private String password;

	@Column(unique = true)
	private String email;

	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	private CustomerStatus customerStatus;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@ManyToOne
	private Role role;

	@Builder.Default
	@OneToMany(mappedBy = "customer")
	private List<Account> accounts = new ArrayList<>();

	public void addAccount(Account account) {
		accounts.add(account);
		account.setCustomer(this);
	}

	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();

		if (customerStatus == null) {
			this.customerStatus = CustomerStatus.ACTIVE;
		}
	}

	@PreUpdate
	public void preUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

}
