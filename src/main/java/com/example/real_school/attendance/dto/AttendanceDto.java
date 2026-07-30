package com.example.real_school.attendance.dto;

import com.example.real_school.common.enums.AttendanceStatus;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AttendanceDto {
    private Long id;
    private Long studentId;
    private Long sectionId;
    private LocalDate date;
    private AttendanceStatus status;
    private String remarks;
}
