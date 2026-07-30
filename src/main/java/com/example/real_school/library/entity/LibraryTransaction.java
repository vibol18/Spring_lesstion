package com.example.real_school.library.entity;

import com.example.real_school.student.entity.Student;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "library_transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private LibraryBook libraryBook;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student; // references borrower

    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Double fineAmount;
    private String status; // ISSUED, RETURNED, OVERDUE
}
