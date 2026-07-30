package com.example.real_school.communication.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime publishedAt;
    private Boolean isActive;
}
