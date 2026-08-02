package com.example.real_school.exam.service.impl;

import com.example.real_school.academic.entity.AcademicYear;
import com.example.real_school.academic.repository.AcademicYearRepository;
import com.example.real_school.exam.dto.ExamDto;
import com.example.real_school.exam.entity.Exam;
import com.example.real_school.exam.repository.ExamRepository;
import com.example.real_school.exam.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final AcademicYearRepository academicYearRepository;

    @Override
    public List<ExamDto> getAllExams() {
        return examRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExamDto createExam(ExamDto dto) {
        Exam exam = new Exam();
        exam.setName(dto.getName());
        exam.setTerm(dto.getTerm());
        if (dto.getAcademicYearId() != null) {
            AcademicYear year = academicYearRepository.findById(dto.getAcademicYearId())
                    .orElseThrow(() -> new RuntimeException("Academic year not found"));
            exam.setAcademicYear(year);
        }
        return mapToDto(examRepository.save(exam));
    }

    @Override
    public ExamDto getExamById(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        return mapToDto(exam);
    }
    @Override
    public ExamDto updateExam(Long id, ExamDto dto) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        exam.setName(dto.getName());
        exam.setTerm(dto.getTerm());
        if (dto.getAcademicYearId() != null) {
            AcademicYear year = academicYearRepository.findById(dto.getAcademicYearId())
                    .orElseThrow(() -> new RuntimeException("Academic year not found"));
            exam.setAcademicYear(year);
        } else {
            exam.setAcademicYear(null);
        }
        return mapToDto(examRepository.save(exam));
    }

    private ExamDto mapToDto(Exam exam) {
        ExamDto dto = new ExamDto();
        dto.setId(exam.getId());
        dto.setName(exam.getName());
        dto.setTerm(exam.getTerm());
        if (exam.getAcademicYear() != null) {
            dto.setAcademicYearId(exam.getAcademicYear().getId());
        }
        return dto;
    }
}
