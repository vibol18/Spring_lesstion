package com.example.real_school.attendance.controller;

import com.example.real_school.attendance.dto.AttendanceDto;
import com.example.real_school.attendance.dto.AttendanceSummaryDto;
import com.example.real_school.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<AttendanceDto> markAttendance(@RequestBody AttendanceDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceService.markAttendance(dto));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceDto>> getStudentAttendance(@PathVariable Long studentId) {
        return ResponseEntity.ok(attendanceService.getStudentAttendance(studentId));
    }

    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<AttendanceDto>> getSectionAttendanceByDate(
            @PathVariable Long sectionId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(attendanceService.getSectionAttendanceByDate(sectionId, date));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceDto> updateAttendance(@PathVariable Long id, @RequestBody AttendanceDto dto) {
        return ResponseEntity.ok(attendanceService.updateAttendance(id, dto));
    }

    @GetMapping("/student/{studentId}/summary")
    public ResponseEntity<AttendanceSummaryDto> getStudentAttendanceSummary(@PathVariable Long studentId) {
        return ResponseEntity.ok(attendanceService.getStudentAttendanceSummary(studentId));
    }
}
