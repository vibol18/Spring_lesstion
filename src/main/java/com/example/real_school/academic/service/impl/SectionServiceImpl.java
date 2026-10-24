package com.example.real_school.academic.service.impl;

import com.example.real_school.academic.dto.SectionDto;
import com.example.real_school.academic.entity.SchoolClass;
import com.example.real_school.academic.entity.Section;
import com.example.real_school.academic.repository.SchoolClassRepository;
import com.example.real_school.academic.repository.SectionRepository;
import com.example.real_school.academic.service.SectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final SchoolClassRepository schoolClassRepository;

    public SectionServiceImpl(SectionRepository sectionRepository,
                              SchoolClassRepository schoolClassRepository) {
        this.sectionRepository = sectionRepository;
        this.schoolClassRepository = schoolClassRepository;
    }

    @Override
    public List<SectionDto> getAllSections() {
        return sectionRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SectionDto getSectionById(Long id) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found with ID: " + id));
        return mapToDto(section);
    }

    @Override
    public SectionDto createSection(SectionDto dto) {
        Section section = new Section();
        section.setName(dto.getName());
        if (dto.getClassId() != null) {
            SchoolClass sc = schoolClassRepository.findById(dto.getClassId())
                    .orElseThrow(() -> new RuntimeException("Class not found with ID: " + dto.getClassId()));
            section.setSchoolClass(sc);
        }
        return mapToDto(sectionRepository.save(section));
    }

    @Override
    public SectionDto updateSection(Long id, SectionDto dto) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found with ID: " + id));
        section.setName(dto.getName());
        if (dto.getClassId() != null) {
            SchoolClass sc = schoolClassRepository.findById(dto.getClassId())
                    .orElseThrow(() -> new RuntimeException("Class not found with ID: " + dto.getClassId()));
            section.setSchoolClass(sc);
        }
        return mapToDto(sectionRepository.save(section));
    }

    @Override
    public void deleteSection(Long id) {
        if (!sectionRepository.existsById(id)) {
            throw new RuntimeException("Section not found with ID: " + id);
        }
        sectionRepository.deleteById(id);
    }

    @Override
    public List<SectionDto> getSectionsByClassId(Long classId) {
        return sectionRepository.findAll().stream()
                .filter(s -> s.getSchoolClass() != null && s.getSchoolClass().getId().equals(classId))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private SectionDto mapToDto(Section section) {
        SectionDto dto = new SectionDto();
        dto.setId(section.getId());
        dto.setName(section.getName());
        if (section.getSchoolClass() != null) {
            dto.setClassId(section.getSchoolClass().getId());
        }
        return dto;
    }
}
