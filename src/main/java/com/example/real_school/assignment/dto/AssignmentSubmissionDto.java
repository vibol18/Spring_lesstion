package com.example.real_school.assignment.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AssignmentSubmissionDto {
    private Long id;
    private Long assignmentId;
    private Long studentId;
    private String fileUrl;
    private LocalDateTime submittedAt;
    private Double marks;
    private String feedback;
    private String status;
}
