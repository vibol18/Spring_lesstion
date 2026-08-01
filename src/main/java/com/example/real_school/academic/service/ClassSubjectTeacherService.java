package com.example.real_school.academic.service;

import com.example.real_school.academic.dto.ClassSubjectTeacherDto;
import java.util.List;

public interface ClassSubjectTeacherService {
    ClassSubjectTeacherDto assign(ClassSubjectTeacherDto dto);
    List<ClassSubjectTeacherDto> getBySectionId(Long sectionId);
    void deleteAssignment(Long id);
}
