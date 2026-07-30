package com.example.real_school.attendance.repository;

import com.example.real_school.attendance.entity.StaffAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffAttendanceRepository extends JpaRepository<StaffAttendance, Long> {
}
