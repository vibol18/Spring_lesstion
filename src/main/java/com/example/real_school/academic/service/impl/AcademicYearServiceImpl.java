package com.example.real_school.academic.service.impl;

import com.example.real_school.academic.dto.AcademicYearDto;
import com.example.real_school.academic.entity.AcademicYear;
import com.example.real_school.academic.repository.AcademicYearRepository;
import com.example.real_school.academic.service.AcademicYearService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AcademicYearServiceImpl implements AcademicYearService {

    private final AcademicYearRepository academicYearRepository;

    public AcademicYearServiceImpl(AcademicYearRepository academicYearRepository) {
        this.academicYearRepository = academicYearRepository;
    }

    @Override
    public List<AcademicYearDto> getAllAcademicYears() {
        return academicYearRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AcademicYearDto getAcademicYearById(Long id) {
        AcademicYear year = academicYearRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic year not found with ID: " + id));
        return mapToDto(year);
    }

    @Override
    public AcademicYearDto createAcademicYear(AcademicYearDto dto) {
        AcademicYear year = AcademicYear.builder()
                .name(dto.getYearName())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .isCurrent(dto.getCurrent() != null ? dto.getCurrent() : false)
                .build();
        return mapToDto(academicYearRepository.save(year));
    }

    @Override
    public AcademicYearDto updateAcademicYear(Long id, AcademicYearDto dto) {
        AcademicYear year = academicYearRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic year not found with ID: " + id));
        year.setName(dto.getYearName());
        year.setStartDate(dto.getStartDate());
        year.setEndDate(dto.getEndDate());
        if (dto.getCurrent() != null) {
            year.setIsCurrent(dto.getCurrent());
        }
        return mapToDto(academicYearRepository.save(year));
    }

    @Override
    public AcademicYearDto setCurrentAcademicYear(Long id) {
        // Unset all current flags first
        List<AcademicYear> allYears = academicYearRepository.findAll();
        allYears.forEach(y -> y.setIsCurrent(false));
        academicYearRepository.saveAll(allYears);

        // Now set the target as current
        AcademicYear year = academicYearRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic year not found with ID: " + id));
        year.setIsCurrent(true);
        return mapToDto(academicYearRepository.save(year));
    }

    private AcademicYearDto mapToDto(AcademicYear year) {
        AcademicYearDto dto = new AcademicYearDto();
        dto.setId(year.getId());
        dto.setYearName(year.getName());
        dto.setStartDate(year.getStartDate());
        dto.setEndDate(year.getEndDate());
        dto.setCurrent(year.getIsCurrent());
        return dto;
    }
}
