package com.example.real_school.teacher.entity;

import com.example.real_school.academic.entity.Subject;
import com.example.real_school.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String employeeId;
    private String qualification;
    private String experienceYears;
    private LocalDate dateOfJoining;

    @ManyToMany(mappedBy = "teachers")
    @Builder.Default
    private Set<Subject> subjects = new HashSet<>();
}
