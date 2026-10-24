package com.example.real_school.auth.service;

import com.example.real_school.auth.dto.*;

public interface AuthService {
    LoginResponse authenticateUser(LoginRequest loginRequest);
    void registerUser(RegisterRequest registerRequest);
    LoginResponse refreshToken(RefreshTokenRequest request);
    void logout();
    void forgotPassword(ForgotPasswordRequest request);
    void resetPassword(ResetPasswordRequest request);
}
