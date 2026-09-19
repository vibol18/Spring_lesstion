package com.example.real_school.attendance.service;

import com.example.real_school.attendance.dto.StaffAttendanceDto;
import java.time.LocalDate;
import java.util.List;

public interface StaffAttendanceService {
    StaffAttendanceDto markStaffAttendance(StaffAttendanceDto dto);
    List<StaffAttendanceDto> getStaffAttendance(Long staffId);
    List<StaffAttendanceDto> getAttendanceByDate(LocalDate date);
    List<StaffAttendanceDto> getAttendanceByMonth(int year, int month);
    List<StaffAttendanceDto> getAttendanceByMonthAndStaff(int year, int month, Long staffId);
    StaffAttendanceDto updateStaffAttendance(Long id, StaffAttendanceDto dto);
    void deleteAttendance(Long id);
}