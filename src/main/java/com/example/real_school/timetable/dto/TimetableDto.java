package com.example.real_school.timetable.dto;

import lombok.Data;
import java.time.LocalTime;

@Data
public class TimetableDto {
    private Long id;
    private Long sectionId;
    private Long subjectId;
    private Long teacherId;
    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private String classroom;
}
