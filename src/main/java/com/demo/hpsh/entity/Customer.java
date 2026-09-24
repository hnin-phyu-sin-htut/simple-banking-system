package com.demo.hpsh.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.demo.hpsh.enums.CustomerStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private String email;
	private String phoneNumber;
	
	@Enumerated(EnumType.STRING)
	private CustomerStatus customerStatus;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@Builder.Default
	@OneToMany(mappedBy = "customer")
	private List<Account> accounts = new ArrayList<>();
	
	public void addAccount(Customer customer) {
		Account account = new Account();
		accounts.add(account);
	}
	
	@PrePersist
	public void prePersist() {
		LocalDateTime createdAt = LocalDateTime.now();
		LocalDateTime updatedAt = LocalDateTime.now();
		
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		
		if (customerStatus == null) {
			this.customerStatus = CustomerStatus.ACTIVE;
		}
	}
	
	@PreUpdate
	public void preUpdate() {
		LocalDateTime updatedAt = LocalDateTime.now();
		this.updatedAt = updatedAt;
	}

}
