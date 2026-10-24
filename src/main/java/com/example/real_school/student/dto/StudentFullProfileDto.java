package com.example.real_school.student.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentFullProfileDto {
    private Long id;
    private String admissionNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String bloodGroup;
    private String address;

    // From User entity
    private String username;
    private String email;

    // Linked Parents
    private List<ParentDto> parents;
}