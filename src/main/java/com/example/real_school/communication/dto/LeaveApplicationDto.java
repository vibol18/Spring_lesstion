package com.example.real_school.communication.dto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveApplicationDto {
    private Long id;
    private String applicantName;
    private String reason;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status; // PENDING, APPROVED, REJECTED
    private LocalDateTime appliedAt;
}
