package com.example.real_school.student.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private Long id;
    private String admissionNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String bloodGroup;
    private String address;

    // Added User fields
    private String firstName;
    private String lastName;
    private String email;
}