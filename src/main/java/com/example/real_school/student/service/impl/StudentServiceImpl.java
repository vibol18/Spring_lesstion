package com.example.real_school.student.service.impl;

import com.example.real_school.student.dto.ParentDto;
import com.example.real_school.student.dto.StudentDto;
import com.example.real_school.student.dto.StudentFullProfileDto;
import com.example.real_school.student.service.ParentService;
import com.example.real_school.student.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService, ParentService {

    // StudentService
    @Override
    public List<StudentDto> getAllStudents() { return Collections.emptyList(); }
    @Override
    public StudentDto getStudentById(Long id) { return new StudentDto(); }
    @Override
    public StudentDto createStudent(StudentDto studentDto) { return studentDto; }
    @Override
    public StudentDto updateStudent(Long id, StudentDto studentDto) { studentDto.setId(id); return studentDto; }
    @Override
    public void deleteStudent(Long id) {}
    @Override
    public List<StudentDto> getStudentsBySectionId(Long sectionId) { return Collections.emptyList(); }
    @Override
    public StudentFullProfileDto getFullProfile(Long id) { return new StudentFullProfileDto(); }
    @Override
    public void linkParent(Long studentId, Long parentId) {}

    // ParentService
    @Override
    public List<ParentDto> getAllParents() { return Collections.emptyList(); }
    @Override
    public ParentDto getParentById(Long id) { return new ParentDto(); }
    @Override
    public ParentDto createParent(ParentDto parentDto) { return parentDto; }
    @Override
    public ParentDto updateParent(Long id, ParentDto parentDto) { parentDto.setId(id); return parentDto; }
    @Override
    public List<StudentDto> getChildren(Long id) { return Collections.emptyList(); }
}
