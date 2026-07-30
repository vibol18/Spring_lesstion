package com.example.real_school.assignment.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AssignmentDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Long sectionId;
    private Long subjectId;
    private String fileUrl;
}
