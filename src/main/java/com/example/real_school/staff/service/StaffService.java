package com.example.real_school.staff.service;

import com.example.real_school.staff.dto.StaffDto;
import java.util.List;

public interface StaffService {
    List<StaffDto> getAllStaff();
    StaffDto getStaffById(Long id);
    StaffDto createStaff(StaffDto dto);
    StaffDto updateStaff(Long id, StaffDto dto);
    void deleteStaff(Long id);
}
