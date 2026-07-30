package com.example.real_school.hostel.entity;

import com.example.real_school.student.entity.Student;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "hostel_allocations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HostelAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private HostelRoom hostelRoom;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private LocalDate allocationDate;
    private LocalDate vacantDate;
    private Boolean isActive;
}
