package com.example.real_school.teacher.service;

import com.example.real_school.academic.dto.SubjectDto;
import com.example.real_school.teacher.dto.TeacherDto;
import com.example.real_school.timetable.dto.TimetableDto;

import java.util.List;

public interface TeacherService {
    List<TeacherDto> getAllTeachers();
    TeacherDto getTeacherById(Long id);
    TeacherDto createTeacher(TeacherDto dto);
    TeacherDto updateTeacher(Long id, TeacherDto dto);
    void deleteTeacher(Long id);
    List<SubjectDto> getTeacherSubjects(Long id);
    List<TimetableDto> getTeacherTimetable(Long id);
}
