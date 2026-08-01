package com.example.real_school.auth.service.impl;

import com.example.real_school.auth.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Override
    public void sendPasswordResetEmail(String toEmail, String resetToken) {
        // TODO: implement
    }
}
