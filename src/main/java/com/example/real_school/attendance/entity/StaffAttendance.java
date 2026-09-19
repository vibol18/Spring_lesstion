package com.example.real_school.attendance.entity;

import com.example.real_school.common.enums.AttendanceStatus;
import com.example.real_school.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "staff_attendance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; 

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    private String remarks;
}
