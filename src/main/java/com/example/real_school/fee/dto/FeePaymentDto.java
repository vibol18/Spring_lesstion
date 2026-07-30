package com.example.real_school.fee.dto;

import com.example.real_school.common.enums.PaymentMode;
import com.example.real_school.common.enums.PaymentStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FeePaymentDto {
    private Long id;
    private Long studentId;
    private Long feeCategoryId;
    private Double amountPaid;
    private LocalDateTime paymentDate;
    private PaymentMode paymentMode;
    private PaymentStatus status;
    private String transactionId;
}
