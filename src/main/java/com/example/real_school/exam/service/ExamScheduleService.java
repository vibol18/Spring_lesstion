package com.example.real_school.exam.service;

import com.example.real_school.exam.dto.ExamScheduleDto;
import java.util.List;

public interface ExamScheduleService {
    List<ExamScheduleDto> getSchedulesByExam(Long examId);
    ExamScheduleDto createSchedule(ExamScheduleDto dto);
    ExamScheduleDto updateSchedule(Long id, ExamScheduleDto dto);
}
