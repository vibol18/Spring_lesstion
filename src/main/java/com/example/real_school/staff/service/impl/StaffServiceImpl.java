package com.example.real_school.staff.service.impl;

import com.example.real_school.staff.dto.StaffDto;
import com.example.real_school.staff.service.StaffService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    @Override
    public List<StaffDto> getAllStaff() { return Collections.emptyList(); }

    @Override
    public StaffDto getStaffById(Long id) { return new StaffDto(); }

    @Override
    public StaffDto createStaff(StaffDto dto) { return dto; }

    @Override
    public StaffDto updateStaff(Long id, StaffDto dto) { dto.setId(id); return dto; }

    @Override
    public void deleteStaff(Long id) {}
}
