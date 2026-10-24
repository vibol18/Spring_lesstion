package com.example.real_school.notification.listener;

import com.example.real_school.notification.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditEventListener {
    private final AuditLogRepository auditLogRepository;
}
