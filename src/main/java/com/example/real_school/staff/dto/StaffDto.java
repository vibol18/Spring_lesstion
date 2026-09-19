package com.example.real_school.staff.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class StaffDto {
    private Long id;
    private String employeeId;
    private String designation;
    private String department;
    private LocalDate dateOfJoining;

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}