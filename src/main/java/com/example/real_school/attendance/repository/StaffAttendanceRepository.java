package com.example.real_school.attendance.repository;

import com.example.real_school.attendance.entity.StaffAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StaffAttendanceRepository extends JpaRepository<StaffAttendance, Long> {
    List<StaffAttendance> findByUserIdOrderByDateDesc(Long userId);
    Optional<StaffAttendance> findByUserIdAndDate(Long userId, LocalDate date);
    List<StaffAttendance> findByDate(LocalDate date);
    List<StaffAttendance> findByDateBetweenOrderByDateAsc(LocalDate start, LocalDate end);
}
