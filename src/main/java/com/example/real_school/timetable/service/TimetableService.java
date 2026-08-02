package com.example.real_school.timetable.service;

import com.example.real_school.timetable.dto.TimetableDto;
import java.util.List;

public interface TimetableService {
    List<TimetableDto> getTimetableBySection(Long sectionId);
    TimetableDto createTimetableEntry(TimetableDto dto);
    TimetableDto updateTimetableEntry(Long id, TimetableDto dto);
    void deleteTimetableEntry(Long id);
}
