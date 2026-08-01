package com.example.real_school.academic.service;

import com.example.real_school.academic.dto.SubjectDto;
import java.util.List;

public interface SubjectService {
    List<SubjectDto> getAllSubjects();
    SubjectDto getSubjectById(Long id);
    SubjectDto createSubject(SubjectDto dto);
    SubjectDto updateSubject(Long id, SubjectDto dto);
    void deleteSubject(Long id);
    List<SubjectDto> getSubjectsByClassId(Long classId);
}
