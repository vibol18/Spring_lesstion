package com.example.real_school.academic.service.impl;

import com.example.real_school.academic.service.SubjectService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Collections;
import com.example.real_school.academic.dto.SubjectDto;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Override
    public List<SubjectDto> getAllSubjects() {
        return Collections.emptyList();
    }
    @Override
    public SubjectDto getSubjectById(Long id) {
        return null;
    }
    @Override
    public SubjectDto createSubject(SubjectDto dto) {
        return null;
    }
    @Override
    public SubjectDto updateSubject(Long id, SubjectDto dto) {
        return null;
    }
    @Override
    public void deleteSubject(Long id) {
        // TODO: implement
    }
    @Override
    public List<SubjectDto> getSubjectsByClassId(Long classId) {
        return Collections.emptyList();
    }
}
