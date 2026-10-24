package com.example.real_school.academic.service;

import com.example.real_school.academic.dto.AcademicYearDto;
import java.util.List;

public interface AcademicYearService {
    List<AcademicYearDto> getAllAcademicYears();
    AcademicYearDto getAcademicYearById(Long id);
    AcademicYearDto createAcademicYear(AcademicYearDto dto);
    AcademicYearDto updateAcademicYear(Long id, AcademicYearDto dto);
    AcademicYearDto setCurrentAcademicYear(Long id);
}
