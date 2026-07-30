package com.example.real_school.exam.entity;

import com.example.real_school.academic.entity.SchoolClass;
import com.example.real_school.academic.entity.Subject;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "exam_schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Double maxMarks;
    private Double passMarks;
}
