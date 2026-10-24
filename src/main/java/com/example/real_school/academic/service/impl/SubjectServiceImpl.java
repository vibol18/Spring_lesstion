package com.example.real_school.academic.service.impl;

import com.example.real_school.academic.dto.SubjectDto;
import com.example.real_school.academic.entity.Subject;
import com.example.real_school.academic.repository.SubjectRepository;
import com.example.real_school.academic.service.SubjectService;
import com.example.real_school.teacher.entity.Teacher;
import com.example.real_school.teacher.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository, TeacherRepository teacherRepository) {
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<SubjectDto> getAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDto getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + id));
        return mapToDto(subject);
    }

    @Override
    public SubjectDto createSubject(SubjectDto dto) {
        Subject subject = Subject.builder()
                .name(dto.getName())
                .code(dto.getCode())
                .build();
        attachTeachers(subject, dto.getTeacherIds());
        return mapToDto(subjectRepository.save(subject));
    }

    @Override
    public SubjectDto updateSubject(Long id, SubjectDto dto) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + id));
        subject.setName(dto.getName());
        subject.setCode(dto.getCode());
        attachTeachers(subject, dto.getTeacherIds());
        return mapToDto(subjectRepository.save(subject));
    }

    @Override
    public void deleteSubject(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new RuntimeException("Subject not found with ID: " + id);
        }
        subjectRepository.deleteById(id);
    }

    @Override
    public List<SubjectDto> getSubjectsByClassId(Long classId) {
        // Basic implementation — returns all subjects (extend with ClassSubjectTeacher join if needed)
        return subjectRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private void attachTeachers(Subject subject, List<Long> teacherIds) {
        if (teacherIds == null || teacherIds.isEmpty()) {
            subject.setTeachers(Set.of());
            return;
        }
        List<Teacher> teachers = teacherRepository.findAllById(teacherIds);
        subject.setTeachers(teachers.stream().collect(Collectors.toSet()));
    }

    private SubjectDto mapToDto(Subject subject) {
        SubjectDto dto = new SubjectDto();
        dto.setId(subject.getId());
        dto.setName(subject.getName());
        dto.setCode(subject.getCode());
        dto.setTeacherIds(subject.getTeachers().stream()
                .map(Teacher::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}