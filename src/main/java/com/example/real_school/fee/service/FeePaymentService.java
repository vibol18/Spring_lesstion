package com.example.real_school.fee.service;

import com.example.real_school.fee.dto.FeePaymentDto;
import java.util.List;

public interface FeePaymentService {
    FeePaymentDto makePayment(FeePaymentDto dto);
    List<FeePaymentDto> getAllPayments();
    FeePaymentDto getPaymentById(Long id);
    List<FeePaymentDto> getPaymentsByStudent(Long studentId);
    List<FeePaymentDto> getPendingPayments();
    byte[] generateReceiptPdf(Long paymentId);
}
