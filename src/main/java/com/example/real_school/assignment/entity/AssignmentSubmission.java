package com.example.real_school.assignment.entity;

import com.example.real_school.student.entity.Student;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "assignment_submissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String fileUrl;
    private LocalDateTime submittedAt;
    private Double marks;
    private String feedback;
    private String status; // SUBMITTED, GRADED, LATE
}
