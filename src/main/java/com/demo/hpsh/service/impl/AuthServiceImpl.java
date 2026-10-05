package com.demo.hpsh.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.hpsh.dao.CustomerDao;
import com.demo.hpsh.dao.RoleDao;
import com.demo.hpsh.dto.LoginRequest;
import com.demo.hpsh.dto.LoginResponse;
import com.demo.hpsh.dto.RegisterRequest;
import com.demo.hpsh.dto.RegisterResponse;
import com.demo.hpsh.entity.Customer;
import com.demo.hpsh.entity.Role;
import com.demo.hpsh.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final CustomerDao customerDao;
	private final RoleDao roleDao;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	@Override
	public RegisterResponse register(RegisterRequest registerRequest) {
		if (customerDao.findByUsername(registerRequest.username()).isPresent()) {
			throw new RuntimeException("Username already exists.");
		}

		Role role = roleDao.findByRoleName(registerRequest.roleName()).orElseGet(() -> {
			Role newRole = new Role();
			newRole.setRoleName(registerRequest.roleName());
			return roleDao.save(newRole);
		});

		Customer customer = Customer.builder().name(registerRequest.name()).username(registerRequest.username())
				.password(passwordEncoder.encode(registerRequest.password())).email(registerRequest.email())
				.phoneNumber(registerRequest.phoneNumber()).role(role).build();
		Customer savedUser = customerDao.save(customer);

		return new RegisterResponse(savedUser.getId(), savedUser.getName(), savedUser.getUsername(),
				savedUser.getEmail(), savedUser.getPhoneNumber(), savedUser.getRole().getRoleName());
	}

	@Override
	public LoginResponse login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
		Customer user = customerDao.findByUsername(authentication.getName())
				.orElseThrow(() -> new RuntimeException("User not found."));

		return new LoginResponse(user.getId(), user.getUsername(), user.getRole().getRoleName(), "Login Success.");
	}

}
