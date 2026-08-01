package com.example.real_school.attendance.service.impl;

import com.example.real_school.attendance.dto.AttendanceDto;
import com.example.real_school.attendance.dto.AttendanceSummaryDto;
import com.example.real_school.attendance.dto.StaffAttendanceDto;
import com.example.real_school.attendance.service.AttendanceService;
import com.example.real_school.attendance.service.StaffAttendanceService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService, StaffAttendanceService {

    // AttendanceService
    @Override
    public AttendanceDto markAttendance(AttendanceDto dto) { return dto; }

    @Override
    public List<AttendanceDto> getStudentAttendance(Long studentId) { return Collections.emptyList(); }

    @Override
    public List<AttendanceDto> getSectionAttendanceByDate(Long sectionId, LocalDate date) { return Collections.emptyList(); }

    @Override
    public AttendanceDto updateAttendance(Long id, AttendanceDto dto) { return dto; }

    @Override
    public AttendanceSummaryDto getStudentAttendanceSummary(Long studentId) {
        return new AttendanceSummaryDto();
    }

    // StaffAttendanceService
    @Override
    public StaffAttendanceDto markStaffAttendance(StaffAttendanceDto dto) { return dto; }

    @Override
    public List<StaffAttendanceDto> getStaffAttendance(Long staffId) { return Collections.emptyList(); }
}
