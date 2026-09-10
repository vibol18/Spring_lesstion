package com.example.real_school.twofactor.service;

import com.example.real_school.twofactor.dto.*;

public interface TwoFactorService {

    TwoFactorStatusDto getStatus(Long userId);

    void sendEnableCode(Long userId, EnableTwoFactorRequest request);

    void verifyAndEnable(Long userId, VerifyTwoFactorRequest request);

    void disable(Long userId, DisableTwoFactorRequest request);

    void updatePhone(Long userId, UpdatePhoneRequest request);
}