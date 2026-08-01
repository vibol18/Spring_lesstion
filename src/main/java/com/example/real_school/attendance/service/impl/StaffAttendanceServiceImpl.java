package com.example.real_school.attendance.service.impl;

import com.example.real_school.attendance.dto.StaffAttendanceDto;
import com.example.real_school.attendance.service.StaffAttendanceService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StaffAttendanceServiceImpl implements StaffAttendanceService {

    @Override
    public StaffAttendanceDto markStaffAttendance(StaffAttendanceDto dto) {
        return null;
    }

    @Override
    public List<StaffAttendanceDto> getStaffAttendance(Long staffId) {
        return Collections.emptyList();
    }
}
