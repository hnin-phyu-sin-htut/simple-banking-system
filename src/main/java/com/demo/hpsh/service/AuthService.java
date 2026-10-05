package com.demo.hpsh.service;

import com.demo.hpsh.dto.LoginRequest;
import com.demo.hpsh.dto.LoginResponse;
import com.demo.hpsh.dto.RegisterRequest;
import com.demo.hpsh.dto.RegisterResponse;

public interface AuthService {

	RegisterResponse register(RegisterRequest registerRequest);

	LoginResponse login(LoginRequest loginRequest);

}
