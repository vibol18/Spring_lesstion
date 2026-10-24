package com.example.real_school.exam.controller;

import com.example.real_school.exam.dto.ExamScheduleDto;
import com.example.real_school.exam.service.ExamScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/exam-schedule")
@RequiredArgsConstructor
public class ExamScheduleController {

    private final ExamScheduleService examScheduleService;

    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<ExamScheduleDto>> getByExam(@PathVariable Long examId) {
        return ResponseEntity.ok(examScheduleService.getSchedulesByExam(examId));
    }

    @PostMapping
    public ResponseEntity<ExamScheduleDto> create(@RequestBody ExamScheduleDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(examScheduleService.createSchedule(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamScheduleDto> update(@PathVariable Long id, @RequestBody ExamScheduleDto dto) {
        return ResponseEntity.ok(examScheduleService.updateSchedule(id, dto));
    }
}
