package com.example.real_school.auth.service.impl;

import com.example.real_school.auth.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final JavaMailSender mailSender;

    @Value("${app.mail.from}")
    private String fromAddress;

    @Value("${app.frontend.reset-password-url}")
    private String resetPasswordUrl;

    @Override
    public void sendPasswordResetEmail(String toEmail, String resetToken) {
        try {
            String resetLink = resetPasswordUrl + "?token=" + resetToken;

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromAddress);
            message.setTo(toEmail);
            message.setSubject("Reset your School Management password");
            message.setText(
                    "Hello,\n\n" +
                    "We received a request to reset your password.\n" +
                    "Click the link below to set a new password. This link expires in 30 minutes.\n\n" +
                    resetLink + "\n\n" +
                    "If you did not request this, please ignore this email.\n\n" +
                    "- School Management System"
            );

            mailSender.send(message);
        } catch (Exception ex) {
            logger.error("Failed to send password reset email to {}: {}", toEmail, ex.getMessage());
        }
    }
}