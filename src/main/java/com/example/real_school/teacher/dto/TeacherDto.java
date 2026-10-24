package com.example.real_school.teacher.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TeacherDto {
    private Long id;
    private String employeeId;
    private String qualification;
    private String experienceYears;
    private LocalDate dateOfJoining;
}
