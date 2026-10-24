package com.example.real_school.notification.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDto {
    private Long id;
    private String title;
    private String message;
    private String recipientType;
    private Long recipientId;
    private Boolean isRead;
    private LocalDateTime createdAt;
}
