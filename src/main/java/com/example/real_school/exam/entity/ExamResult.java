package com.example.real_school.exam.entity;

import com.example.real_school.student.entity.Student;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exam_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exam_schedule_id")
    private ExamSchedule examSchedule;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private Double marksObtained;
    private String grade;
    private String remarks;
}
