package com.example.real_school.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceSummaryDto {
    private Long studentId;
    private long totalDays;
    private long presentDays;
    private long absentDays;
    private double percentage;
}
