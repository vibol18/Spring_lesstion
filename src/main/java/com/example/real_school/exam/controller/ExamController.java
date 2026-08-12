package com.example.real_school.exam.controller;

import com.example.real_school.exam.dto.ExamDto;
import com.example.real_school.exam.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @GetMapping
    public ResponseEntity<List<ExamDto>> getAll() {
        return ResponseEntity.ok(examService.getAllExams());
    }

    @PostMapping
    public ResponseEntity<ExamDto> create(@RequestBody ExamDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(examService.createExam(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(examService.getExamById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamDto> update(@PathVariable Long id, @RequestBody ExamDto dto) {
        return ResponseEntity.ok(examService.updateExam(id, dto));
    }
}
