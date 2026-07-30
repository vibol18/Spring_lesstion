package com.example.real_school.transport.entity;

import com.example.real_school.student.entity.Student;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_transport")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentTransport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private TransportRoute transportRoute;

    @ManyToOne
    @JoinColumn(name = "stop_id")
    private TransportStop transportStop;
}
