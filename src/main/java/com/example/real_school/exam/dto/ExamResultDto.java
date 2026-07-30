package com.example.real_school.exam.dto;

import lombok.Data;

@Data
public class ExamResultDto {
    private Long id;
    private Long examScheduleId;
    private Long studentId;
    private Double marksObtained;
    private String grade;
    private String remarks;
}
