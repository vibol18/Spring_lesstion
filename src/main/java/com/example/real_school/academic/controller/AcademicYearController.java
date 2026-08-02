package com.example.real_school.academic.controller;

import com.example.real_school.academic.dto.AcademicYearDto;
import com.example.real_school.academic.service.AcademicYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/academic-years")
@RequiredArgsConstructor
public class AcademicYearController {

    private final AcademicYearService academicYearService;

    @GetMapping
    public ResponseEntity<List<AcademicYearDto>> getAll() {
        return ResponseEntity.ok(academicYearService.getAllAcademicYears());
    }

    @PostMapping
    public ResponseEntity<AcademicYearDto> create(@RequestBody AcademicYearDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(academicYearService.createAcademicYear(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademicYearDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(academicYearService.getAcademicYearById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcademicYearDto> update(@PathVariable Long id, @RequestBody AcademicYearDto dto) {
        return ResponseEntity.ok(academicYearService.updateAcademicYear(id, dto));
    }

    @PatchMapping("/{id}/set-current")
    public ResponseEntity<AcademicYearDto> setCurrent(@PathVariable Long id) {
        return ResponseEntity.ok(academicYearService.setCurrentAcademicYear(id));
    }
}
