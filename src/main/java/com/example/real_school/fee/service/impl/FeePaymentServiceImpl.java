package com.example.real_school.fee.service.impl;

import com.example.real_school.common.enums.PaymentStatus;
import com.example.real_school.fee.dto.FeePaymentDto;
import com.example.real_school.fee.entity.FeeCategory;
import com.example.real_school.fee.entity.FeePayment;
import com.example.real_school.fee.repository.FeeCategoryRepository;
import com.example.real_school.fee.repository.FeePaymentRepository;
import com.example.real_school.fee.service.FeePaymentService;
import com.example.real_school.student.entity.Student;
import com.example.real_school.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FeePaymentServiceImpl implements FeePaymentService {

    private final FeePaymentRepository paymentRepository;
    private final FeeCategoryRepository categoryRepository;
    private final StudentRepository studentRepository;

    public FeePaymentServiceImpl(FeePaymentRepository paymentRepository,
                                 FeeCategoryRepository categoryRepository,
                                 StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.categoryRepository = categoryRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public FeePaymentDto makePayment(FeePaymentDto dto) {
        FeePayment payment = new FeePayment();
        
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        payment.setStudent(student);
        
        FeeCategory category = categoryRepository.findById(dto.getFeeCategoryId())
                .orElseThrow(() -> new RuntimeException("Fee Category not found"));
        payment.setFeeCategory(category);
        
        payment.setAmountPaid(dto.getAmountPaid());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentMode(dto.getPaymentMode());
        payment.setStatus(PaymentStatus.PAID); // Simplifying for now
        payment.setTransactionId(dto.getTransactionId());
        
        return mapToDto(paymentRepository.save(payment));
    }

    @Override
    public List<FeePaymentDto> getPaymentsByStudent(Long studentId) {
        return paymentRepository.findAll().stream()
                .filter(p -> p.getStudent() != null && p.getStudent().getId().equals(studentId))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeePaymentDto> getPendingPayments() {
        return paymentRepository.findAll().stream()
                .filter(p -> p.getStatus() == PaymentStatus.PENDING)
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public byte[] generateReceiptPdf(Long paymentId) {
        // Mock PDF generation
        return new byte[0];
    }
    
    private FeePaymentDto mapToDto(FeePayment payment) {
        FeePaymentDto dto = new FeePaymentDto();
        dto.setId(payment.getId());
        dto.setStudentId(payment.getStudent().getId());
        dto.setFeeCategoryId(payment.getFeeCategory().getId());
        dto.setAmountPaid(payment.getAmountPaid());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setPaymentMode(payment.getPaymentMode());
        dto.setStatus(payment.getStatus());
        dto.setTransactionId(payment.getTransactionId());
        return dto;
    }
}
