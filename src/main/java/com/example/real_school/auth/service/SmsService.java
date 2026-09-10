package com.example.real_school.auth.service;

public interface SmsService {
    void sendTwoFactorCode(String toPhone, String code);
}