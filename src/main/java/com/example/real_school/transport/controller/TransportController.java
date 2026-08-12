package com.example.real_school.transport.controller;

import com.example.real_school.transport.dto.StudentTransportDto;
import com.example.real_school.transport.dto.TransportRouteDto;
import com.example.real_school.transport.dto.TransportStopDto;
import com.example.real_school.transport.service.TransportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/transport")
@RequiredArgsConstructor
public class TransportController {

    private final TransportService transportService;

    // --- Routes ---
    @GetMapping("/routes")
    public ResponseEntity<List<TransportRouteDto>> getAllRoutes() {
        return ResponseEntity.ok(transportService.getAllRoutes());
    }

    @PostMapping("/routes")
    public ResponseEntity<TransportRouteDto> createRoute(@RequestBody TransportRouteDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transportService.createRoute(dto));
    }

    @GetMapping("/routes/{id}")
    public ResponseEntity<TransportRouteDto> getRouteById(@PathVariable Long id) {
        return ResponseEntity.ok(transportService.getRouteById(id));
    }

    @PutMapping("/routes/{id}")
    public ResponseEntity<TransportRouteDto> updateRoute(@PathVariable Long id, @RequestBody TransportRouteDto dto) {
        return ResponseEntity.ok(transportService.updateRoute(id, dto));
    }

    @DeleteMapping("/routes/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        transportService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }

    // --- Stops ---
    @GetMapping("/routes/{routeId}/stops")
    public ResponseEntity<List<TransportStopDto>> getStops(@PathVariable Long routeId) {
        return ResponseEntity.ok(transportService.getStopsByRoute(routeId));
    }

    @PostMapping("/stops")
    public ResponseEntity<TransportStopDto> addStop(@RequestBody TransportStopDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transportService.addStop(dto));
    }

    // --- Student Assignments ---
    @PostMapping("/assignments")
    public ResponseEntity<StudentTransportDto> assignStudent(@RequestBody StudentTransportDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transportService.assignStudentToRoute(dto));
    }

    @GetMapping("/assignments/student/{studentId}")
    public ResponseEntity<List<StudentTransportDto>> getStudentTransport(@PathVariable Long studentId) {
        return ResponseEntity.ok(transportService.getStudentTransport(studentId));
    }
}
