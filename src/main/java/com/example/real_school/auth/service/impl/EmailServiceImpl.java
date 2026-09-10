package com.example.real_school.auth.service.impl;

import com.example.real_school.auth.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    // The JavaMailSender bean is only created by Spring Boot when spring.mail.host
    // (or spring.mail.jndi-name) is configured. It is optional here so the app still
    // runs in dev without SMTP; codes are then logged to the console.
    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Value("${spring.mail.host:}")
    private String mailHost;

    @Value("${app.frontend-url:http://localhost:3000}")
    private String frontendUrl;

    @Override
    public void sendPasswordResetEmail(String toEmail, String resetToken) {
        String resetLink = frontendUrl + "/reset-password?token=" + resetToken;
        sendEmail(toEmail,
                "Reset your password",
                "Hello,\n\nTo reset your password, click the link below:\n" + resetLink +
                        "\n\nIf you did not request this, you can safely ignore this email.");
    }

    @Override
    public void sendTwoFactorCode(String toEmail, String code) {
        sendEmail(toEmail,
                "Your verification code",
                "Hello,\n\nYour verification code is: " + code +
                        "\n\nThis code expires in a few minutes. If you did not request it, you can safely ignore this email.");
    }

    private void sendEmail(String to, String subject, String body) {
        if (StringUtils.hasText(mailHost) && mailSender != null) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(to);
                message.setSubject(subject);
                message.setText(body);
                mailSender.send(message);
                log.info("Email sent to {}", to);
                return;
            } catch (MailException ex) {
                log.warn("Failed to send email to {}: {}", to, ex.getMessage());
            }
        }
        // No SMTP configured or sending failed: log the content so the feature can
        // be tested locally (dev) without a real mail server.
        log.info("[EMAIL fallback] To: {} | Subject: {} | Body: {}",
                to, subject, body.replaceAll("\\r?\\n", " "));
    }
}