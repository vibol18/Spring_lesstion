package com.example.real_school.exam.entity;

import com.example.real_school.academic.entity.AcademicYear;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Midterm, Final...
    private String term;

    @ManyToOne
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYear;
}
