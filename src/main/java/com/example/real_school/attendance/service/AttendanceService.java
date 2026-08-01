package com.example.real_school.attendance.service;

import com.example.real_school.attendance.dto.AttendanceDto;
import com.example.real_school.attendance.dto.AttendanceSummaryDto;
import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {
    AttendanceDto markAttendance(AttendanceDto dto);
    List<AttendanceDto> getStudentAttendance(Long studentId);
    List<AttendanceDto> getSectionAttendanceByDate(Long sectionId, LocalDate date);
    AttendanceDto updateAttendance(Long id, AttendanceDto dto);
    AttendanceSummaryDto getStudentAttendanceSummary(Long studentId);
}
