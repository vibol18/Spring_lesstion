package com.example.real_school.exam.service;

import com.example.real_school.exam.dto.ExamDto;
import java.util.List;

public interface ExamService {
    List<ExamDto> getAllExams();
    ExamDto createExam(ExamDto dto);
    ExamDto getExamById(Long id);
    ExamDto updateExam(Long id, ExamDto dto);
}
