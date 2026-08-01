package com.example.real_school.academic.controller;

import com.example.real_school.academic.dto.ClassSubjectTeacherDto;
import com.example.real_school.academic.service.ClassSubjectTeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-subject-teacher")
@RequiredArgsConstructor
public class ClassSubjectTeacherController {

    private final ClassSubjectTeacherService classSubjectTeacherService;

    @PostMapping
    public ResponseEntity<ClassSubjectTeacherDto> assign(@RequestBody ClassSubjectTeacherDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(classSubjectTeacherService.assign(dto));
    }

    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<ClassSubjectTeacherDto>> getBySectionId(@PathVariable Long sectionId) {
        return ResponseEntity.ok(classSubjectTeacherService.getBySectionId(sectionId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        classSubjectTeacherService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
