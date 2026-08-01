package com.example.real_school.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentFullProfileDto {
    private StudentDto student;
    private Object userDetails;
    private Object currentEnrollment;
    private Object parents;
}
