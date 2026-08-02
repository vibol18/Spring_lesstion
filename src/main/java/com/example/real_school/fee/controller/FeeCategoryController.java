package com.example.real_school.fee.controller;

import com.example.real_school.fee.dto.FeeCategoryDto;
import com.example.real_school.fee.service.FeeCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/fee-categories")
@RequiredArgsConstructor
public class FeeCategoryController {

    private final FeeCategoryService feeCategoryService;

    @GetMapping
    public ResponseEntity<List<FeeCategoryDto>> getAll() {
        return ResponseEntity.ok(feeCategoryService.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<FeeCategoryDto> create(@RequestBody FeeCategoryDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(feeCategoryService.createCategory(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeeCategoryDto> update(@PathVariable Long id, @RequestBody FeeCategoryDto dto) {
        return ResponseEntity.ok(feeCategoryService.updateCategory(id, dto));
    }
}
