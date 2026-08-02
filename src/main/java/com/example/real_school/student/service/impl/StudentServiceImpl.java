package com.example.real_school.student.service.impl;

import com.example.real_school.student.dto.ParentDto;
import com.example.real_school.student.dto.StudentCreateRequest;
import com.example.real_school.student.dto.StudentDto;
import com.example.real_school.student.dto.StudentFullProfileDto;
import com.example.real_school.student.entity.Parent;
import com.example.real_school.student.entity.Student;
import com.example.real_school.student.repository.ParentRepository;
import com.example.real_school.student.repository.StudentRepository;
import com.example.real_school.student.service.StudentService;
import com.example.real_school.user.entity.User;
import com.example.real_school.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentServiceImpl(
            StudentRepository studentRepository,
            ParentRepository parentRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.parentRepository = parentRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
        return mapToDto(student);
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = Student.builder()
                .admissionNumber(studentDto.getAdmissionNumber())
                .dateOfBirth(studentDto.getDateOfBirth())
                .gender(studentDto.getGender())
                .bloodGroup(studentDto.getBloodGroup())
                .address(studentDto.getAddress())
                .build();

        Student savedStudent = studentRepository.save(student);
        return mapToDto(savedStudent);
    }

    // Overloaded/Additional helper if you are passing StudentCreateRequest
    public StudentDto createStudentWithUser(StudentCreateRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        User savedUser = userRepository.save(user);

        Student student = Student.builder()
                .user(savedUser)
                .admissionNumber(request.getAdmissionNumber())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .build();

        Student savedStudent = studentRepository.save(student);
        return mapToDto(savedStudent);
    }

    @Override
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));

        student.setAdmissionNumber(studentDto.getAdmissionNumber());
        student.setDateOfBirth(studentDto.getDateOfBirth());
        student.setGender(studentDto.getGender());
        student.setBloodGroup(studentDto.getBloodGroup());
        student.setAddress(studentDto.getAddress());

        Student updatedStudent = studentRepository.save(student);
        return mapToDto(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentDto> getStudentsBySectionId(Long sectionId) {
        return Collections.emptyList();
    }

    @Override
    public StudentFullProfileDto getFullProfile(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));

        List<Parent> parents = parentRepository.findAllByChildrenId(id);

        List<ParentDto> parentDtos = parents.stream().map(p -> {
            ParentDto dto = new ParentDto();
            dto.setId(p.getId());
            dto.setOccupation(p.getOccupation());
            dto.setContactNumber(p.getContactNumber());
            dto.setAddress(p.getAddress());
            return dto;
        }).collect(Collectors.toList());

        User user = student.getUser();

        return StudentFullProfileDto.builder()
                .id(student.getId())
                .admissionNumber(student.getAdmissionNumber())
                .dateOfBirth(student.getDateOfBirth())
                .gender(student.getGender())
                .bloodGroup(student.getBloodGroup())
                .address(student.getAddress())
                .username(user != null ? user.getUsername() : null)
                .email(user != null ? user.getEmail() : null)
                .parents(parentDtos)
                .build();
    }

    @Override
    public void linkParent(Long studentId, Long parentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        Parent parent = parentRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Parent not found with ID: " + parentId));

        parent.getChildren().add(student);
        parentRepository.save(parent);
    }

    // Helper mapper Entity -> StudentDto
    private StudentDto mapToDto(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .admissionNumber(student.getAdmissionNumber())
                .dateOfBirth(student.getDateOfBirth())
                .gender(student.getGender())
                .bloodGroup(student.getBloodGroup())
                .address(student.getAddress())
                .build();
    }
}