package com.example.real_school.student.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_parents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    private String relationship; // FATHER, MOTHER, GUARDIAN
}
