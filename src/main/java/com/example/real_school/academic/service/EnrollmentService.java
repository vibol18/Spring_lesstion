package com.example.real_school.academic.service;

import com.example.real_school.academic.dto.EnrollmentDto;
import java.util.List;

public interface EnrollmentService {
    List<EnrollmentDto> getAllEnrollments();
    EnrollmentDto getEnrollmentById(Long id);
    EnrollmentDto createEnrollment(EnrollmentDto dto);
    List<EnrollmentDto> getEnrollmentsByStudentId(Long studentId);
    EnrollmentDto updateEnrollment(Long id, EnrollmentDto dto);
}
