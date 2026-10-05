package com.demo.hpsh.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.hpsh.dao.CustomerDao;
import com.demo.hpsh.entity.Customer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final CustomerDao customerDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerDao.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found."));
		return new CustomUserDetails(customer);
	}

}
