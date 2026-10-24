package com.example.real_school.auth.service;

public interface EmailService {
    void sendPasswordResetEmail(String toEmail, String resetToken);
    void sendTwoFactorCode(String toEmail, String code);
}