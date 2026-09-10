package com.example.real_school.twofactor.service.impl;

import com.example.real_school.auth.service.EmailService;
import com.example.real_school.auth.service.SmsService;
import com.example.real_school.common.exception.BadRequestException;
import com.example.real_school.common.exception.ResourceNotFoundException;
import com.example.real_school.twofactor.dto.*;
import com.example.real_school.twofactor.entity.OtpCode;
import com.example.real_school.twofactor.entity.OtpPurpose;
import com.example.real_school.twofactor.repository.OtpCodeRepository;
import com.example.real_school.twofactor.service.TwoFactorService;
import com.example.real_school.user.entity.User;
import com.example.real_school.user.entity.TwoFactorMethod;
import com.example.real_school.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class TwoFactorServiceImpl implements TwoFactorService {

    private final UserRepository userRepository;
    private final OtpCodeRepository otpCodeRepository;
    private final EmailService emailService;
    private final SmsService smsService;
    private final PasswordEncoder passwordEncoder;

    private static final SecureRandom RANDOM = new SecureRandom();

    @Value("${app.otp.expiration-minutes:10}")
    private long otpExpirationMinutes;

    @Value("${app.otp.length:6}")
    private int otpLength;

    @Override
    @Transactional(readOnly = true)
    public TwoFactorStatusDto getStatus(Long userId) {
        User user = findUser(userId);
        return TwoFactorStatusDto.builder()
                .twoFactorEnabled(user.isTwoFactorEnabled())
                .method(user.getTwoFactorMethod())
                .maskedEmail(mask(user.getEmail()))
                .maskedPhone(user.getPhone() != null ? mask(user.getPhone()) : null)
                .build();
    }

    @Override
    @Transactional
    public void sendEnableCode(Long userId, EnableTwoFactorRequest request) {
        User user = findUser(userId);
        TwoFactorMethod method = request.getMethod();

        // A new code invalidates any previous pending code for the same user.
        otpCodeRepository.deleteByUserIdAndPurpose(userId, OtpPurpose.ENABLE_2FA);

        String recipient;
        if (method == TwoFactorMethod.PHONE) {
            String phone = StringUtils.hasText(request.getPhone()) ? request.getPhone() : user.getPhone();
            if (!StringUtils.hasText(phone)) {
                throw new BadRequestException("Phone number is required when using phone-based 2FA");
            }
            user.setPhone(phone);
            recipient = phone;
        } else {
            recipient = user.getEmail();
        }
        userRepository.save(user);

        String code = generateCode();
        OtpCode otp = OtpCode.builder()
                .user(user)
                .purpose(OtpPurpose.ENABLE_2FA)
                .method(method)
                .recipient(recipient)
                .code(code)
                .expiresAt(Instant.now().plus(otpExpirationMinutes, ChronoUnit.MINUTES))
                .used(false)
                .build();
        otpCodeRepository.save(otp);

        if (method == TwoFactorMethod.PHONE) {
            smsService.sendTwoFactorCode(recipient, code);
        } else {
            emailService.sendTwoFactorCode(recipient, code);
        }
    }

    @Override
    @Transactional
    public void verifyAndEnable(Long userId, VerifyTwoFactorRequest request) {
        User user = findUser(userId);
        OtpCode otp = otpCodeRepository
                .findTopByUserIdAndPurposeAndUsedFalseOrderByCreatedAtDesc(userId, OtpPurpose.ENABLE_2FA)
                .orElseThrow(() -> new BadRequestException(
                        "No verification code was sent. Please request a new code."));

        if (otp.getExpiresAt().isBefore(Instant.now())) {
            throw new BadRequestException("Verification code has expired. Please request a new code.");
        }
        if (!otp.getCode().equals(request.getCode().trim())) {
            throw new BadRequestException("Invalid verification code.");
        }

        otp.setUsed(true);
        otpCodeRepository.save(otp);

        user.setTwoFactorEnabled(true);
        user.setTwoFactorMethod(otp.getMethod());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void disable(Long userId, DisableTwoFactorRequest request) {
        User user = findUser(userId);
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Current password is incorrect");
        }
        user.setTwoFactorEnabled(false);
        user.setTwoFactorMethod(null);
        otpCodeRepository.deleteByUserIdAndPurpose(userId, OtpPurpose.ENABLE_2FA);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updatePhone(Long userId, UpdatePhoneRequest request) {
        User user = findUser(userId);
        user.setPhone(request.getPhone().trim());
        userRepository.save(user);
    }

    // ==================== HELPERS ====================

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    private String generateCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < otpLength; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }

    private String mask(String value) {
        if (value == null || value.length() < 4) {
            return "***";
        }
        return value.substring(0, 2) + "****" + value.substring(value.length() - 2);
    }
}