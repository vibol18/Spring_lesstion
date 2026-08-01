package com.example.real_school.teacher.service.impl;

import com.example.real_school.academic.dto.SubjectDto;
import com.example.real_school.teacher.dto.TeacherDto;
import com.example.real_school.teacher.service.TeacherService;
import com.example.real_school.timetable.dto.TimetableDto;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Override
    public List<TeacherDto> getAllTeachers() { return Collections.emptyList(); }

    @Override
    public TeacherDto getTeacherById(Long id) { return new TeacherDto(); }

    @Override
    public TeacherDto createTeacher(TeacherDto dto) { return dto; }

    @Override
    public TeacherDto updateTeacher(Long id, TeacherDto dto) { dto.setId(id); return dto; }

    @Override
    public void deleteTeacher(Long id) {}

    @Override
    public List<SubjectDto> getTeacherSubjects(Long id) { return Collections.emptyList(); }

    @Override
    public List<TimetableDto> getTeacherTimetable(Long id) { return Collections.emptyList(); }
}
