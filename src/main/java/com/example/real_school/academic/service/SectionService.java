package com.example.real_school.academic.service;

import com.example.real_school.academic.dto.SectionDto;
import java.util.List;

public interface SectionService {
    List<SectionDto> getAllSections();
    SectionDto getSectionById(Long id);
    SectionDto createSection(SectionDto dto);
    SectionDto updateSection(Long id, SectionDto dto);
    void deleteSection(Long id);
    List<SectionDto> getSectionsByClassId(Long classId);
}
