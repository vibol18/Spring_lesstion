package com.example.real_school.auth.service;

import com.example.real_school.auth.dto.LoginRequest;
import com.example.real_school.auth.dto.LoginResponse;
import com.example.real_school.auth.dto.RegisterRequest;

public interface AuthService {
    LoginResponse authenticateUser(LoginRequest loginRequest);
    void registerUser(RegisterRequest registerRequest);
}
