package com.example.real_school.academic.service.impl;

import com.example.real_school.academic.service.SectionService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Collections;
import com.example.real_school.academic.dto.SectionDto;
import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {
    @Override
    public List<SectionDto> getAllSections() {
        return Collections.emptyList();
    }
    @Override
    public SectionDto getSectionById(Long id) {
        return null;
    }
    @Override
    public SectionDto createSection(SectionDto dto) {
        return null;
    }
    @Override
    public SectionDto updateSection(Long id, SectionDto dto) {
        return null;
    }
    @Override
    public void deleteSection(Long id) {
        // TODO: implement
    }
    @Override
    public List<SectionDto> getSectionsByClassId(Long classId) {
        return Collections.emptyList();
    }
}
