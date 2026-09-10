package com.example.real_school.assignment.controller;

import com.example.real_school.assignment.dto.AssignmentDto;
import com.example.real_school.assignment.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @GetMapping
    public ResponseEntity<List<AssignmentDto>> getAll() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }

    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<AssignmentDto>> getBySection(@PathVariable Long sectionId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsBySection(sectionId));
    }

    @PostMapping
    public ResponseEntity<AssignmentDto> create(@RequestBody AssignmentDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assignmentService.createAssignment(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(assignmentService.getAssignmentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssignmentDto> update(@PathVariable Long id, @RequestBody AssignmentDto dto) {
        return ResponseEntity.ok(assignmentService.updateAssignment(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}