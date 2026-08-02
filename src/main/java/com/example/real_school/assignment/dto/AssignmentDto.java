package com.example.real_school.assignment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentDto {
    private Long id;
    private String title;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    private Long sectionId;
    private Long subjectId;
    private String fileUrl;
}