package com.example.real_school.attendance.controller;

import com.example.real_school.attendance.dto.StaffAttendanceDto;
import com.example.real_school.attendance.service.StaffAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/staff-attendance")
@RequiredArgsConstructor
public class StaffAttendanceController {

    private final StaffAttendanceService staffAttendanceService;

    @PostMapping
    public ResponseEntity<StaffAttendanceDto> markAttendance(@RequestBody StaffAttendanceDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(staffAttendanceService.markStaffAttendance(dto));
    }

    @GetMapping("/staff/{staffId}")
    public ResponseEntity<List<StaffAttendanceDto>> getStaffAttendance(@PathVariable Long staffId) {
        return ResponseEntity.ok(staffAttendanceService.getStaffAttendance(staffId));
    }

    @GetMapping("/staff/{staffId}/month")
    public ResponseEntity<List<StaffAttendanceDto>> getStaffAttendanceByMonth(
            @PathVariable Long staffId,
            @RequestParam int year,
            @RequestParam int month) {
        return ResponseEntity.ok(staffAttendanceService.getAttendanceByMonthAndStaff(year, month, staffId));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<StaffAttendanceDto>> getAttendanceByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(staffAttendanceService.getAttendanceByDate(date));
    }

    @GetMapping("/month")
    public ResponseEntity<List<StaffAttendanceDto>> getAttendanceByMonth(
            @RequestParam int year,
            @RequestParam int month) {
        return ResponseEntity.ok(staffAttendanceService.getAttendanceByMonth(year, month));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffAttendanceDto> updateAttendance(@PathVariable Long id,
                                                               @RequestBody StaffAttendanceDto dto) {
        return ResponseEntity.ok(staffAttendanceService.updateStaffAttendance(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        staffAttendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
}