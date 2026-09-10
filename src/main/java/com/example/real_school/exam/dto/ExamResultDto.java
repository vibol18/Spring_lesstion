package com.example.real_school.exam.dto;

import lombok.Data;

@Data
public class ExamResultDto {
    private Long id;
    private Long examScheduleId;
    private Long examId;
    private Long subjectId;
    private String subjectName;
    private String examName;
    private Long studentId;
    private Double marksObtained;
    private Double totalMarks;
    private String grade;
    private String remarks;
}
