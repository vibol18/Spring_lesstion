package com.example.real_school.student.service;

import com.example.real_school.student.dto.StudentCreateRequest;
import com.example.real_school.student.dto.StudentDto;
import com.example.real_school.student.dto.StudentFullProfileDto;
import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();
    StudentDto getStudentById(Long id);
    StudentDto createStudent(StudentDto studentDto);
    StudentDto createStudentWithUser(StudentCreateRequest request);
    StudentDto updateStudent(Long id, StudentDto studentDto);
    void deleteStudent(Long id);
    List<StudentDto> getStudentsBySectionId(Long sectionId);
    StudentFullProfileDto getFullProfile(Long id);
    void linkParent(Long studentId, Long parentId);
}
