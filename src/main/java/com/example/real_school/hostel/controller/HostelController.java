package com.example.real_school.hostel.controller;

import com.example.real_school.hostel.dto.HostelAllocationDto;
import com.example.real_school.hostel.dto.HostelBlockDto;
import com.example.real_school.hostel.dto.HostelRoomDto;
import com.example.real_school.hostel.service.HostelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/hostels")
@RequiredArgsConstructor
public class HostelController {

    private final HostelService hostelService;

    // --- Blocks ---
    @GetMapping("/blocks")
    public ResponseEntity<List<HostelBlockDto>> getAllBlocks() {
        return ResponseEntity.ok(hostelService.getAllBlocks());
    }

    @PostMapping("/blocks")
    public ResponseEntity<HostelBlockDto> createBlock(@RequestBody HostelBlockDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hostelService.createBlock(dto));
    }

    // --- Rooms ---
    @GetMapping("/blocks/{blockId}/rooms")
    public ResponseEntity<List<HostelRoomDto>> getRoomsByBlock(@PathVariable Long blockId) {
        return ResponseEntity.ok(hostelService.getRoomsByBlock(blockId));
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<HostelRoomDto>> getAllRooms() {
        return ResponseEntity.ok(hostelService.getAllRooms());
    }

    @PostMapping("/rooms")
    public ResponseEntity<HostelRoomDto> createRoom(@RequestBody HostelRoomDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hostelService.createRoom(dto));
    }

    // --- Allocations ---
    @GetMapping("/allocations")
    public ResponseEntity<List<HostelAllocationDto>> getAllocations() {
        return ResponseEntity.ok(hostelService.getAllocations());
    }

    @PostMapping("/allocations")
    public ResponseEntity<HostelAllocationDto> allocate(@RequestBody HostelAllocationDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hostelService.allocateRoom(dto));
    }

    @PatchMapping("/allocations/{id}/vacate")
    public ResponseEntity<HostelAllocationDto> vacate(@PathVariable Long id) {
        return ResponseEntity.ok(hostelService.vacateRoom(id));
    }

    @GetMapping("/allocations/student/{studentId}")
    public ResponseEntity<List<HostelAllocationDto>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(hostelService.getAllocationsByStudent(studentId));
    }
}
