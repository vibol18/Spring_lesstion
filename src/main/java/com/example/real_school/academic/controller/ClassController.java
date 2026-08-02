package com.example.real_school.academic.controller;

import com.example.real_school.academic.dto.ClassDto;
import com.example.real_school.academic.dto.SectionDto;
import com.example.real_school.academic.dto.SubjectDto;
import com.example.real_school.academic.service.ClassService;
import com.example.real_school.academic.service.SectionService;
import com.example.real_school.academic.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;
    private final SectionService sectionService;
    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<ClassDto>> getAll() {
        return ResponseEntity.ok(classService.getAllClasses());
    }

    @PostMapping
    public ResponseEntity<ClassDto> create(@RequestBody ClassDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(classService.createClass(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(classService.getClassById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassDto> update(@PathVariable Long id, @RequestBody ClassDto dto) {
        return ResponseEntity.ok(classService.updateClass(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        classService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{classId}/sections")
    public ResponseEntity<List<SectionDto>> getSectionsByClass(@PathVariable Long classId) {
        return ResponseEntity.ok(sectionService.getSectionsByClassId(classId));
    }

    @GetMapping("/{classId}/subjects")
    public ResponseEntity<List<SubjectDto>> getSubjectsByClass(@PathVariable Long classId) {
        return ResponseEntity.ok(subjectService.getSubjectsByClassId(classId));
    }
}
