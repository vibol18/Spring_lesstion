package com.example.real_school.attendance.service;

import com.example.real_school.attendance.dto.StaffAttendanceDto;
import java.util.List;

public interface StaffAttendanceService {
    StaffAttendanceDto markStaffAttendance(StaffAttendanceDto dto);
    List<StaffAttendanceDto> getStaffAttendance(Long staffId);
}
