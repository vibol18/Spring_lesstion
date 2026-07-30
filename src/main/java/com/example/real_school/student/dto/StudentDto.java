package com.example.real_school.student.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class StudentDto {
    private Long id;
    private String admissionNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String bloodGroup;
    private String address;
}
