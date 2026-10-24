package com.example.real_school.academic.service;

import com.example.real_school.academic.dto.ClassDto;
import java.util.List;

public interface ClassService {
    List<ClassDto> getAllClasses();
    ClassDto getClassById(Long id);
    ClassDto createClass(ClassDto dto);
    ClassDto updateClass(Long id, ClassDto dto);
    void deleteClass(Long id);
}
