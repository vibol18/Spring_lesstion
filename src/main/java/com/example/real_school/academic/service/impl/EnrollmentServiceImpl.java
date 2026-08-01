package com.example.real_school.academic.service.impl;

import com.example.real_school.academic.service.EnrollmentService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Collections;
import com.example.real_school.academic.dto.EnrollmentDto;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    @Override
    public List<EnrollmentDto> getAllEnrollments() {
        return Collections.emptyList();
    }
    @Override
    public EnrollmentDto createEnrollment(EnrollmentDto dto) {
        return null;
    }
    @Override
    public List<EnrollmentDto> getEnrollmentsByStudentId(Long studentId) {
        return Collections.emptyList();
    }
    @Override
    public EnrollmentDto updateEnrollment(Long id, EnrollmentDto dto) {
        return null;
    }
}
