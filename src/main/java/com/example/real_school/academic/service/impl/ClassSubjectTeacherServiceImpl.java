package com.example.real_school.academic.service.impl;

import com.example.real_school.academic.dto.ClassSubjectTeacherDto;
import com.example.real_school.academic.service.ClassSubjectTeacherService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClassSubjectTeacherServiceImpl implements ClassSubjectTeacherService {

    @Override
    public ClassSubjectTeacherDto assign(ClassSubjectTeacherDto dto) {
        return null;
    }

    @Override
    public List<ClassSubjectTeacherDto> getBySectionId(Long sectionId) {
        return Collections.emptyList();
    }

    @Override
    public void deleteAssignment(Long id) {
        // TODO: implement
    }
}
