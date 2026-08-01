package com.example.real_school.academic.service.impl;

import com.example.real_school.academic.service.AcademicYearService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Collections;
import com.example.real_school.academic.dto.AcademicYearDto;
import java.util.List;

@Service
public class AcademicYearServiceImpl implements AcademicYearService {
    @Override
    public List<AcademicYearDto> getAllAcademicYears() {
        return Collections.emptyList();
    }
    @Override
    public AcademicYearDto getAcademicYearById(Long id) {
        return null;
    }
    @Override
    public AcademicYearDto createAcademicYear(AcademicYearDto dto) {
        return null;
    }
    @Override
    public AcademicYearDto updateAcademicYear(Long id, AcademicYearDto dto) {
        return null;
    }
    @Override
    public AcademicYearDto setCurrentAcademicYear(Long id) {
        return null;
    }
}
