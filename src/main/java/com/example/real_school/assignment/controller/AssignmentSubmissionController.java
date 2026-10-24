package com.example.real_school.assignment.controller;

import com.example.real_school.assignment.dto.AssignmentSubmissionDto;
import com.example.real_school.assignment.service.AssignmentSubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/assignment-submissions")
@RequiredArgsConstructor
public class AssignmentSubmissionController {

    private final AssignmentSubmissionService submissionService;

    @PostMapping
    public ResponseEntity<AssignmentSubmissionDto> submit(@RequestBody AssignmentSubmissionDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(submissionService.submitAssignment(dto));
    }
    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<AssignmentSubmissionDto>> getByAssignment(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(submissionService.getSubmissionsByAssignment(assignmentId));
    }
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AssignmentSubmissionDto>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(submissionService.getSubmissionsByStudent(studentId));
    }
    @PatchMapping("/{id}/grade")
    public ResponseEntity<AssignmentSubmissionDto> grade(
            @PathVariable Long id,
            @RequestParam Double marks,
            @RequestParam(required = false) String feedback) {
        return ResponseEntity.ok(submissionService.gradeSubmission(id, marks, feedback));
    }
}
