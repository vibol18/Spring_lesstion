package com.example.real_school.notification.service;

import com.example.real_school.notification.dto.NotificationDto;
import java.util.List;

public interface NotificationService {
    List<NotificationDto> getAllNotifications();
    List<NotificationDto> getUnreadByRecipient(Long recipientId, String recipientType);
    NotificationDto createNotification(NotificationDto dto);
    NotificationDto markAsRead(Long id);
    void deleteNotification(Long id);
}
