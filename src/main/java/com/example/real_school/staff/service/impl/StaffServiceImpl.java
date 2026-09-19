package com.example.real_school.staff.service.impl;

import com.example.real_school.staff.dto.StaffDto;
import com.example.real_school.staff.entity.Staff;
import com.example.real_school.staff.repository.StaffRepository;
import com.example.real_school.staff.service.StaffService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public List<StaffDto> getAllStaff() {
        return staffRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StaffDto getStaffById(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + id));
        return mapToDto(staff);
    }

    @Override
    public StaffDto createStaff(StaffDto dto) {
        Staff staff = Staff.builder()
                .employeeId(dto.getEmployeeId())
                .designation(dto.getDesignation())
                .department(dto.getDepartment())
                .dateOfJoining(dto.getDateOfJoining())
                .build();
        return mapToDto(staffRepository.save(staff));
    }

    @Override
    public StaffDto updateStaff(Long id, StaffDto dto) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + id));
        
        staff.setEmployeeId(dto.getEmployeeId());
        staff.setDesignation(dto.getDesignation());
        staff.setDepartment(dto.getDepartment());
        staff.setDateOfJoining(dto.getDateOfJoining());
        
        return mapToDto(staffRepository.save(staff));
    }

    @Override
    public void deleteStaff(Long id) {
        if (!staffRepository.existsById(id)) {
            throw new RuntimeException("Staff not found with ID: " + id);
        }
        staffRepository.deleteById(id);
    }

    private StaffDto mapToDto(Staff staff) {
        StaffDto dto = new StaffDto();
        dto.setId(staff.getId());
        dto.setEmployeeId(staff.getEmployeeId());
        dto.setDesignation(staff.getDesignation());
        dto.setDepartment(staff.getDepartment());
        dto.setDateOfJoining(staff.getDateOfJoining());
        if (staff.getUser() != null) {
            dto.setUserId(staff.getUser().getId());
            dto.setFirstName(staff.getUser().getFirstName());
            dto.setLastName(staff.getUser().getLastName());
            dto.setEmail(staff.getUser().getEmail());
            dto.setPhone(staff.getUser().getPhone());
        }
        return dto;
    }
}
