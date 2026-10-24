package com.example.real_school.academic.service.impl;

import com.example.real_school.academic.dto.ClassDto;
import com.example.real_school.academic.entity.SchoolClass;
import com.example.real_school.academic.repository.SchoolClassRepository;
import com.example.real_school.academic.service.ClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClassServiceImpl implements ClassService {

    private final SchoolClassRepository schoolClassRepository;

    public ClassServiceImpl(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    @Override
    public List<ClassDto> getAllClasses() {
        return schoolClassRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClassDto getClassById(Long id) {
        SchoolClass schoolClass = schoolClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found with ID: " + id));
        return mapToDto(schoolClass);
    }

    @Override
    public ClassDto createClass(ClassDto dto) {
        SchoolClass schoolClass = SchoolClass.builder()
                .name(dto.getName())
                .code(dto.getCode())
                .build();
        return mapToDto(schoolClassRepository.save(schoolClass));
    }

    @Override
    public ClassDto updateClass(Long id, ClassDto dto) {
        SchoolClass schoolClass = schoolClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found with ID: " + id));
        schoolClass.setName(dto.getName());
        schoolClass.setCode(dto.getCode());
        return mapToDto(schoolClassRepository.save(schoolClass));
    }

    @Override
    public void deleteClass(Long id) {
        if (!schoolClassRepository.existsById(id)) {
            throw new RuntimeException("Class not found with ID: " + id);
        }
        schoolClassRepository.deleteById(id);
    }

    private ClassDto mapToDto(SchoolClass sc) {
        ClassDto dto = new ClassDto();
        dto.setId(sc.getId());
        dto.setName(sc.getName());
        dto.setCode(sc.getCode());
        return dto;
    }
}
