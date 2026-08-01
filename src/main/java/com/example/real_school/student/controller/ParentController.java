package com.example.real_school.student.controller;

import com.example.real_school.student.dto.ParentDto;
import com.example.real_school.student.dto.StudentDto;
import com.example.real_school.student.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;

    @GetMapping
    public ResponseEntity<List<ParentDto>> getAll() {
        return ResponseEntity.ok(parentService.getAllParents());
    }

    @PostMapping
    public ResponseEntity<ParentDto> create(@RequestBody ParentDto parentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parentService.createParent(parentDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(parentService.getParentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParentDto> update(@PathVariable Long id, @RequestBody ParentDto parentDto) {
        return ResponseEntity.ok(parentService.updateParent(id, parentDto));
    }

    @GetMapping("/{id}/children")
    public ResponseEntity<List<StudentDto>> getChildren(@PathVariable Long id) {
        return ResponseEntity.ok(parentService.getChildren(id));
    }
}
