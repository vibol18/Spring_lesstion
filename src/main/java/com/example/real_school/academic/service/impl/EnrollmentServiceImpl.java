package com.example.real_school.academic.service.impl;

import com.example.real_school.academic.dto.EnrollmentDto;
import com.example.real_school.academic.entity.AcademicYear;
import com.example.real_school.academic.entity.Enrollment;
import com.example.real_school.academic.entity.Section;
import com.example.real_school.academic.repository.AcademicYearRepository;
import com.example.real_school.academic.repository.EnrollmentRepository;
import com.example.real_school.academic.repository.SectionRepository;
import com.example.real_school.academic.service.EnrollmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final SectionRepository sectionRepository;
    private final AcademicYearRepository academicYearRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository,
                                 SectionRepository sectionRepository,
                                 AcademicYearRepository academicYearRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.sectionRepository = sectionRepository;
        this.academicYearRepository = academicYearRepository;
    }

    @Override
    public List<EnrollmentDto> getAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EnrollmentDto getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with ID: " + id));
    }

    @Override
    public EnrollmentDto createEnrollment(EnrollmentDto dto) {
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(dto.getStudentId());
        enrollment.setEnrollmentDate(dto.getEnrollmentDate());

        if (dto.getSectionId() != null) {
            Section section = sectionRepository.findById(dto.getSectionId())
                    .orElseThrow(() -> new RuntimeException("Section not found with ID: " + dto.getSectionId()));
            enrollment.setSection(section);
        }
        if (dto.getAcademicYearId() != null) {
            AcademicYear year = academicYearRepository.findById(dto.getAcademicYearId())
                    .orElseThrow(() -> new RuntimeException("Academic year not found with ID: " + dto.getAcademicYearId()));
            enrollment.setAcademicYear(year);
        }
        return mapToDto(enrollmentRepository.save(enrollment));
    }

    @Override
    public List<EnrollmentDto> getEnrollmentsByStudentId(Long studentId) {
        return enrollmentRepository.findAll().stream()
                .filter(e -> studentId.equals(e.getStudentId()))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EnrollmentDto updateEnrollment(Long id, EnrollmentDto dto) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with ID: " + id));
        enrollment.setStudentId(dto.getStudentId());
        enrollment.setEnrollmentDate(dto.getEnrollmentDate());

        if (dto.getSectionId() != null) {
            Section section = sectionRepository.findById(dto.getSectionId())
                    .orElseThrow(() -> new RuntimeException("Section not found with ID: " + dto.getSectionId()));
            enrollment.setSection(section);
        }
        if (dto.getAcademicYearId() != null) {
            AcademicYear year = academicYearRepository.findById(dto.getAcademicYearId())
                    .orElseThrow(() -> new RuntimeException("Academic year not found with ID: " + dto.getAcademicYearId()));
            enrollment.setAcademicYear(year);
        }
        return mapToDto(enrollmentRepository.save(enrollment));
    }

    private EnrollmentDto mapToDto(Enrollment enrollment) {
        EnrollmentDto dto = new EnrollmentDto();
        dto.setId(enrollment.getId());
        dto.setStudentId(enrollment.getStudentId());
        dto.setEnrollmentDate(enrollment.getEnrollmentDate());
        if (enrollment.getSection() != null) {
            dto.setSectionId(enrollment.getSection().getId());
        }
        if (enrollment.getAcademicYear() != null) {
            dto.setAcademicYearId(enrollment.getAcademicYear().getId());
        }
        return dto;
    }
}
