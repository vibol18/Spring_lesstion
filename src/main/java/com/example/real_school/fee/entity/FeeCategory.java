package com.example.real_school.fee.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fee_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Tuition, Exam, Hostel...
    private Double amount;
    private String description;
}
