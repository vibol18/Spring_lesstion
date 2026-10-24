package com.example.real_school.academic.controller;

import com.example.real_school.academic.dto.SectionDto;
import com.example.real_school.academic.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/sections")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @GetMapping
    public ResponseEntity<List<SectionDto>> getAll() {
        return ResponseEntity.ok(sectionService.getAllSections());
    }

    @PostMapping
    public ResponseEntity<SectionDto> create(@RequestBody SectionDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sectionService.createSection(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(sectionService.getSectionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectionDto> update(@PathVariable Long id, @RequestBody SectionDto dto) {
        return ResponseEntity.ok(sectionService.updateSection(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sectionService.deleteSection(id);
        return ResponseEntity.noContent().build();
    }
}
