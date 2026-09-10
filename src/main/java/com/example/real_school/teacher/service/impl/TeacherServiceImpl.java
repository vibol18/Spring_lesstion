package com.example.real_school.teacher.service.impl;

import com.example.real_school.academic.dto.SubjectDto;
import com.example.real_school.teacher.dto.TeacherDto;
import com.example.real_school.teacher.entity.Teacher;
import com.example.real_school.teacher.repository.TeacherRepository;
import com.example.real_school.teacher.service.TeacherService;
import com.example.real_school.timetable.dto.TimetableDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    @Override
    public List<TeacherDto> getAllTeachers() {
        return teacherRepository.findAll().stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }
    @Override
    public TeacherDto getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + id));
        return mapToDto(teacher);
    }
    @Override
    public TeacherDto createTeacher(TeacherDto dto) {
        Teacher teacher = Teacher.builder()
                .employeeId(dto.getEmployeeId())
                .qualification(dto.getQualification())
                .experienceYears(dto.getExperienceYears())
                .dateOfJoining(dto.getDateOfJoining())
                .build();
        return mapToDto(teacherRepository.save(teacher));
    }
    @Override
    public TeacherDto updateTeacher(Long id, TeacherDto dto) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + id));
        teacher.setEmployeeId(dto.getEmployeeId());
        teacher.setQualification(dto.getQualification());
        teacher.setExperienceYears(dto.getExperienceYears());
        teacher.setDateOfJoining(dto.getDateOfJoining());
        
        return mapToDto(teacherRepository.save(teacher));
    }
    @Override
    public void deleteTeacher(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new RuntimeException("Teacher not found with ID: " + id);
        }
        teacherRepository.deleteById(id);
    }
    @Override
    public List<SubjectDto> getTeacherSubjects(Long id) {
        // Basic impl, extend with ClassSubjectTeacher joins if needed
        return Collections.emptyList();
    }
    @Override
    public List<TimetableDto> getTeacherTimetable(Long id) {
        return Collections.emptyList();
    }
    private TeacherDto mapToDto(Teacher teacher) {
        TeacherDto dto = new TeacherDto();
        dto.setId(teacher.getId());
        dto.setEmployeeId(teacher.getEmployeeId());
        dto.setQualification(teacher.getQualification());
        dto.setExperienceYears(teacher.getExperienceYears());
        dto.setDateOfJoining(teacher.getDateOfJoining());
        return dto;
    }
}
