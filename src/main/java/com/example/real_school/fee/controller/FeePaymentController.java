package com.example.real_school.fee.controller;

import com.example.real_school.fee.dto.FeePaymentDto;
import com.example.real_school.fee.service.FeePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/fee-payments")
@RequiredArgsConstructor
public class FeePaymentController {

    private final FeePaymentService feePaymentService;

    @PostMapping
    public ResponseEntity<FeePaymentDto> makePayment(@RequestBody FeePaymentDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(feePaymentService.makePayment(dto));
    }

    @GetMapping
    public ResponseEntity<List<FeePaymentDto>> getAll() {
        return ResponseEntity.ok(feePaymentService.getAllPayments());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<FeePaymentDto>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(feePaymentService.getPaymentsByStudent(studentId));
    }

    @GetMapping("/pending")
    public ResponseEntity<List<FeePaymentDto>> getPending() {
        return ResponseEntity.ok(feePaymentService.getPendingPayments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeePaymentDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(feePaymentService.getPaymentById(id));
    }

    @GetMapping("/{id}/receipt-pdf")
    public ResponseEntity<byte[]> getReceiptPdf(@PathVariable Long id) {
        // Return dummy bytes for now
        return ResponseEntity.ok(feePaymentService.generateReceiptPdf(id));
    }
}
