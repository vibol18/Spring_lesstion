package com.example.real_school.academic.service.impl;

import com.example.real_school.academic.service.ClassService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Collections;
import com.example.real_school.academic.dto.ClassDto;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Override
    public List<ClassDto> getAllClasses() {
        return Collections.emptyList();
    }
    @Override
    public ClassDto getClassById(Long id) {
        return null;
    }
    @Override
    public ClassDto createClass(ClassDto dto) {
        return null;
    }
    @Override
    public ClassDto updateClass(Long id, ClassDto dto) {
        return null;
    }
    @Override
    public void deleteClass(Long id) {
        // TODO: implement
    }
}
