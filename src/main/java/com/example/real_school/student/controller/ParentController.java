package com.example.real_school.student.controller;

import com.example.real_school.student.dto.ParentDto;
import com.example.real_school.student.dto.StudentDto;
import com.example.real_school.student.service.ParentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/parents")
public class ParentController {

    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @GetMapping
    public ResponseEntity<List<ParentDto>> getAllParents() {
        return ResponseEntity.ok(parentService.getAllParents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentDto> getParentById(@PathVariable Long id) {
        return ResponseEntity.ok(parentService.getParentById(id));
    }

    @PostMapping
    public ResponseEntity<ParentDto> createParent(@RequestBody ParentDto parentDto) {
        return new ResponseEntity<>(parentService.createParent(parentDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParentDto> updateParent(@PathVariable Long id, @RequestBody ParentDto parentDto) {
        return ResponseEntity.ok(parentService.updateParent(id, parentDto));
    }

    @GetMapping("/{id}/children")
    public ResponseEntity<List<StudentDto>> getChildren(@PathVariable Long id) {
        return ResponseEntity.ok(parentService.getChildren(id));
    }
}