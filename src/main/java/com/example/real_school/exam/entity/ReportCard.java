package com.example.real_school.exam.entity;

import com.example.real_school.student.entity.Student;
import com.example.real_school.academic.entity.AcademicYear;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "report_cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYear;

    private Double totalMarks;
    private Double percentage;
    private String overallGrade;
    private String resultStatus; // PASS, FAIL
}
