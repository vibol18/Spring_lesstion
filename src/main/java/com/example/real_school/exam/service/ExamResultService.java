package com.example.real_school.exam.service;

import com.example.real_school.exam.dto.ExamResultDto;
import java.util.List;

public interface ExamResultService {
    List<ExamResultDto> getResultsByStudent(Long studentId);
    List<ExamResultDto> getResultsByExamAndSubject(Long examId, Long subjectId);
    ExamResultDto createResult(ExamResultDto dto);
    ExamResultDto updateResult(Long id, ExamResultDto dto);
}
