package com.example.real_school.auth.service.impl;

import com.example.real_school.auth.dto.LoginRequest;
import com.example.real_school.auth.dto.LoginResponse;
import com.example.real_school.auth.dto.RegisterRequest;
import com.example.real_school.auth.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public LoginResponse authenticateUser(LoginRequest loginRequest) { return null; }
    @Override
    public void registerUser(RegisterRequest registerRequest) {}
}
