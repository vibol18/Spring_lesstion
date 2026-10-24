package com.example.real_school.communication.controller;

import com.example.real_school.communication.dto.LeaveApplicationDto;
import com.example.real_school.communication.service.LeaveApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/leave-applications")
@RequiredArgsConstructor
public class LeaveApplicationController {

    private final LeaveApplicationService leaveApplicationService;

    @GetMapping
    public ResponseEntity<List<LeaveApplicationDto>> getAll() {
        return ResponseEntity.ok(leaveApplicationService.getAllLeaveApplications());
    }

    @PostMapping
    public ResponseEntity<LeaveApplicationDto> apply(@RequestBody LeaveApplicationDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(leaveApplicationService.applyForLeave(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveApplicationDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(leaveApplicationService.getLeaveApplicationById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<LeaveApplicationDto> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(leaveApplicationService.updateStatus(id, status));
    }
}
