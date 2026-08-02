package com.example.real_school.academic.controller;

import com.example.real_school.academic.dto.EnrollmentDto;
import com.example.real_school.academic.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<List<EnrollmentDto>> getAll() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @PostMapping
    public ResponseEntity<EnrollmentDto> create(@RequestBody EnrollmentDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.createEnrollment(dto));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentDto>> getByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByStudentId(studentId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentDto> update(@PathVariable Long id, @RequestBody EnrollmentDto dto) {
        return ResponseEntity.ok(enrollmentService.updateEnrollment(id, dto));
    }
}
