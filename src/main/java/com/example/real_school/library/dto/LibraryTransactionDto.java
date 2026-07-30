package com.example.real_school.library.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class LibraryTransactionDto {
    private Long id;
    private Long bookId;
    private Long studentId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Double fineAmount;
    private String status;
}
