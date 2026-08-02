package com.example.real_school.teacher.controller;

import com.example.real_school.academic.dto.SubjectDto;
import com.example.real_school.teacher.dto.TeacherDto;
import com.example.real_school.teacher.service.TeacherService;
import com.example.real_school.timetable.dto.TimetableDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAll() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @PostMapping
    public ResponseEntity<TeacherDto> create(@RequestBody TeacherDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> update(@PathVariable Long id, @RequestBody TeacherDto dto) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/subjects")
    public ResponseEntity<List<SubjectDto>> getSubjects(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getTeacherSubjects(id));
    }

    @GetMapping("/{id}/timetable")
    public ResponseEntity<List<TimetableDto>> getTimetable(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getTeacherTimetable(id));
    }
}
