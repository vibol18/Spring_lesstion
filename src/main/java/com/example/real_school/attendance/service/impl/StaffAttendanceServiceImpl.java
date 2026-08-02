package com.example.real_school.attendance.service.impl;

import com.example.real_school.attendance.dto.StaffAttendanceDto;
import com.example.real_school.attendance.entity.StaffAttendance;
import com.example.real_school.attendance.repository.StaffAttendanceRepository;
import com.example.real_school.attendance.service.StaffAttendanceService;
import com.example.real_school.common.enums.AttendanceStatus;
import com.example.real_school.staff.entity.Staff;
import com.example.real_school.staff.repository.StaffRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StaffAttendanceServiceImpl implements StaffAttendanceService {

    private final StaffAttendanceRepository staffAttendanceRepository;
    private final StaffRepository staffRepository;

    public StaffAttendanceServiceImpl(StaffAttendanceRepository staffAttendanceRepository,
                                      StaffRepository staffRepository) {
        this.staffAttendanceRepository = staffAttendanceRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public StaffAttendanceDto markStaffAttendance(StaffAttendanceDto dto) {
        Staff staff = staffRepository.findById(dto.getStaffId())
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        StaffAttendance attendance = new StaffAttendance();
        attendance.setUser(staff.getUser()); // Linking the User from Staff
        attendance.setDate(dto.getAttendanceDate());
        attendance.setStatus(AttendanceStatus.valueOf(dto.getStatus()));
        attendance.setRemarks(dto.getRemark());

        return mapToDto(staffAttendanceRepository.save(attendance), dto.getStaffId());
    }

    @Override
    public List<StaffAttendanceDto> getStaffAttendance(Long staffId) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        
        return staffAttendanceRepository.findAll().stream()
                .filter(a -> a.getUser() != null && a.getUser().getId().equals(staff.getUser().getId()))
                .map(a -> mapToDto(a, staffId))
                .collect(Collectors.toList());
    }
    
    private StaffAttendanceDto mapToDto(StaffAttendance attendance, Long staffId) {
        StaffAttendanceDto dto = new StaffAttendanceDto();
        dto.setId(attendance.getId());
        dto.setStaffId(staffId);
        dto.setAttendanceDate(attendance.getDate());
        dto.setStatus(attendance.getStatus().name());
        dto.setRemark(attendance.getRemarks());
        return dto;
    }
}
