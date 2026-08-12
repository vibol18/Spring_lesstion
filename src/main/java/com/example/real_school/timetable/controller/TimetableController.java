package com.example.real_school.timetable.controller;

import com.example.real_school.timetable.dto.TimetableDto;
import com.example.real_school.timetable.service.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/timetables")
@RequiredArgsConstructor
public class TimetableController {

    private final TimetableService timetableService;

    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<TimetableDto>> getBySection(@PathVariable Long sectionId) {
        return ResponseEntity.ok(timetableService.getTimetableBySection(sectionId));
    }

    @PostMapping
    public ResponseEntity<TimetableDto> create(@RequestBody TimetableDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(timetableService.createTimetableEntry(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimetableDto> update(@PathVariable Long id, @RequestBody TimetableDto dto) {
        return ResponseEntity.ok(timetableService.updateTimetableEntry(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        timetableService.deleteTimetableEntry(id);
        return ResponseEntity.noContent().build();
    }
}
