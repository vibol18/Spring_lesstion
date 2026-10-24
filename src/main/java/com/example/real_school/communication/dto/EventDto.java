package com.example.real_school.communication.dto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate eventDate;
    private String location;
    private String organizer;
    private LocalDateTime createdAt;
}
