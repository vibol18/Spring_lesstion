package com.example.real_school.auth.service.impl;

import com.example.real_school.auth.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Override
    public void sendTwoFactorCode(String toPhone, String code) {
        // No SMS provider (e.g. Twilio) is configured yet, so the code is only
        // written to the application log. Wire up a provider here to send a real SMS.
        log.info("[SMS] Sending 2FA code to {}: {}", toPhone, code);
    }
}