package com.example.real_school.exam.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ExamScheduleDto {
    private Long id;
    private Long examId;
    private Long classId;
    private Long subjectId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Double maxMarks;
    private Double passMarks;
}
