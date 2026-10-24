package com.example.real_school.student.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class StudentCreateRequest {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String admissionNumber;
    private LocalDate dateOfBirth;
    private String gender;
}
