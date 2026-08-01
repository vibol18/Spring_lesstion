package com.example.real_school.student.controller;

import com.example.real_school.student.dto.StudentDto;
import com.example.real_school.student.dto.StudentFullProfileDto;
import com.example.real_school.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAll() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping
    public ResponseEntity<StudentDto> create(@RequestBody StudentDto studentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(studentDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> update(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.updateStudent(id, studentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<StudentDto>> getBySectionId(@PathVariable Long sectionId) {
        return ResponseEntity.ok(studentService.getStudentsBySectionId(sectionId));
    }

    @GetMapping("/{id}/full-profile")
    public ResponseEntity<StudentFullProfileDto> getFullProfile(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getFullProfile(id));
    }

    @PostMapping("/{studentId}/parents/{parentId}")
    public ResponseEntity<String> linkParent(@PathVariable Long studentId, @PathVariable Long parentId) {
        studentService.linkParent(studentId, parentId);
        return ResponseEntity.ok("Parent linked to student successfully");
    }
}
