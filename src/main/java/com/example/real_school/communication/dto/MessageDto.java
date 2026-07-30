package com.example.real_school.communication.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {
    private Long id;
    private String senderName;
    private String receiverName;
    private String subject;
    private String body;
    private Boolean isRead;
    private LocalDateTime sentAt;
}
