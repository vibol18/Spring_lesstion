package com.example.real_school.academic.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "class_subject_teachers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassSubjectTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private Long teacherId; // References teacher
}
