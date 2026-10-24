package com.example.real_school.exam.dto;

import lombok.Data;

@Data
public class ExamDto {
    private Long id;
    private String name;
    private String term;
    private Long academicYearId;
}
