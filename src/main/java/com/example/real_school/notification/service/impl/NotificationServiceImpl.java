package com.example.real_school.notification.service.impl;

import com.example.real_school.notification.dto.NotificationDto;
import com.example.real_school.notification.entity.Notification;
import com.example.real_school.notification.repository.NotificationRepository;
import com.example.real_school.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public List<NotificationDto> getAllNotifications() {
        return notificationRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<NotificationDto> getUnreadByRecipient(Long recipientId, String recipientType) {
        return notificationRepository.findAll().stream()
                .filter(n -> n.getRecipientId() != null && n.getRecipientId().equals(recipientId)
                        && recipientType.equalsIgnoreCase(n.getRecipientType())
                        && Boolean.FALSE.equals(n.getIsRead()))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationDto createNotification(NotificationDto dto) {
        Notification notification = Notification.builder()
                .title(dto.getTitle())
                .message(dto.getMessage())
                .recipientType(dto.getRecipientType())
                .recipientId(dto.getRecipientId())
                .isRead(false)
                .createdAt(java.time.LocalDateTime.now())
                .build();
        return mapToDto(notificationRepository.save(notification));
    }

    @Override
    public NotificationDto markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
        notification.setIsRead(true);
        return mapToDto(notificationRepository.save(notification));
    }

    @Override
    public void deleteNotification(Long id) {
        if (!notificationRepository.existsById(id))
            throw new RuntimeException("Notification not found with id: " + id);
        notificationRepository.deleteById(id);
    }
    private NotificationDto mapToDto(Notification n) {
        NotificationDto dto = new NotificationDto();
        dto.setId(n.getId());
        dto.setTitle(n.getTitle());
        dto.setMessage(n.getMessage());
        dto.setRecipientType(n.getRecipientType());
        dto.setRecipientId(n.getRecipientId());
        dto.setIsRead(n.getIsRead());
        dto.setCreatedAt(n.getCreatedAt());
        return dto;
    }
}
