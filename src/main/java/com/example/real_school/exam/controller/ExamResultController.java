package com.example.real_school.exam.controller;

import com.example.real_school.exam.dto.ExamResultDto;
import com.example.real_school.exam.service.ExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/exam-results")
@RequiredArgsConstructor
public class ExamResultController {

    private final ExamResultService examResultService;

    @PostMapping
    public ResponseEntity<ExamResultDto> create(@RequestBody ExamResultDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(examResultService.createResult(dto));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ExamResultDto>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(examResultService.getResultsByStudent(studentId));
    }

    @GetMapping("/exam/{examId}/subject/{subjectId}")
    public ResponseEntity<List<ExamResultDto>> getByExamAndSubject(@PathVariable Long examId, @PathVariable Long subjectId) {
        return ResponseEntity.ok(examResultService.getResultsByExamAndSubject(examId, subjectId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamResultDto> update(@PathVariable Long id, @RequestBody ExamResultDto dto) {
        return ResponseEntity.ok(examResultService.updateResult(id, dto));
    }
}
