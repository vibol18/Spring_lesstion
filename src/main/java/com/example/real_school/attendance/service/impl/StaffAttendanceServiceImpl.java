package com.example.real_school.attendance.service.impl;

import com.example.real_school.attendance.dto.StaffAttendanceDto;
import com.example.real_school.attendance.entity.StaffAttendance;
import com.example.real_school.attendance.repository.StaffAttendanceRepository;
import com.example.real_school.attendance.service.StaffAttendanceService;
import com.example.real_school.common.enums.AttendanceStatus;
import com.example.real_school.common.exception.ResourceNotFoundException;
import com.example.real_school.staff.entity.Staff;
import com.example.real_school.staff.repository.StaffRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
        Staff staff = findStaff(dto.getStaffId());

        if (staff.getUser() == null) {
            throw new IllegalArgumentException(
                    "Staff (ID: " + dto.getStaffId() + ") has no linked user account. Attendance requires a linked user.");
        }

        AttendanceStatus status = parseStatus(dto.getStatus());

        // Upsert: if an attendance row already exists for the same user + date, update it.
        Optional<StaffAttendance> existing =
                staffAttendanceRepository.findByUserIdAndDate(staff.getUser().getId(), dto.getAttendanceDate());
        StaffAttendance attendance;
        if (existing.isPresent()) {
            attendance = existing.get();
            attendance.setStatus(status);
            attendance.setRemarks(dto.getRemark());
        } else {
            attendance = StaffAttendance.builder()
                    .user(staff.getUser())
                    .date(dto.getAttendanceDate())
                    .status(status)
                    .remarks(dto.getRemark())
                    .build();
        }

        return mapToDto(staffAttendanceRepository.save(attendance), dto.getStaffId());
    }

    @Override
    public List<StaffAttendanceDto> getStaffAttendance(Long staffId) {
        Staff staff = findStaff(staffId);
        if (staff.getUser() == null) {
            return Collections.emptyList();
        }
        return staffAttendanceRepository.findByUserIdOrderByDateDesc(staff.getUser().getId()).stream()
                .map(a -> mapToDto(a, staffId))
                .collect(Collectors.toList());
    }

    @Override
    public List<StaffAttendanceDto> getAttendanceByDate(LocalDate date) {
        return staffAttendanceRepository.findByDate(date).stream()
                .map(a -> toDtoWithStaffId(a, a.getUser() == null ? null : a.getUser().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<StaffAttendanceDto> getAttendanceByMonth(int year, int month) {
        YearMonth ym = YearMonth.of(year, month);
        return getBetween(ym.atDay(1), ym.atEndOfMonth());
    }

    @Override
    public List<StaffAttendanceDto> getAttendanceByMonthAndStaff(int year, int month, Long staffId) {
        Staff staff = findStaff(staffId);
        if (staff.getUser() == null) {
            return Collections.emptyList();
        }
        YearMonth ym = YearMonth.of(year, month);
        return staffAttendanceRepository
                .findByDateBetweenOrderByDateAsc(ym.atDay(1), ym.atEndOfMonth()).stream()
                .filter(a -> a.getUser() != null && a.getUser().getId().equals(staff.getUser().getId()))
                .map(a -> mapToDto(a, staffId))
                .collect(Collectors.toList());
    }

    @Override
    public StaffAttendanceDto updateStaffAttendance(Long id, StaffAttendanceDto dto) {
        StaffAttendance attendance = staffAttendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff attendance not found with ID: " + id));
        attendance.setStatus(parseStatus(dto.getStatus()));
        attendance.setRemarks(dto.getRemark());
        attendance.setDate(dto.getAttendanceDate());
        return mapToDto(staffAttendanceRepository.save(attendance), dto.getStaffId());
    }

    @Override
    public void deleteAttendance(Long id) {
        if (!staffAttendanceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Staff attendance not found with ID: " + id);
        }
        staffAttendanceRepository.deleteById(id);
    }

    private List<StaffAttendanceDto> getBetween(LocalDate start, LocalDate end) {
        return staffAttendanceRepository.findByDateBetweenOrderByDateAsc(start, end).stream()
                .map(a -> toDtoWithStaffId(a, a.getUser() == null ? null : a.getUser().getId()))
                .collect(Collectors.toList());
    }

    private Staff findStaff(Long staffId) {
        return staffRepository.findById(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with ID: " + staffId));
    }

    private AttendanceStatus parseStatus(String status) {
        try {
            return AttendanceStatus.valueOf(status == null ? "" : status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid attendance status: " + status);
        }
    }

    private StaffAttendanceDto toDtoWithStaffId(StaffAttendance attendance, Long userId) {
        Long staffId = null;
        if (userId != null) {
            staffId = staffRepository.findByUserId(userId).map(Staff::getId).orElse(null);
        }
        return mapToDto(attendance, staffId);
    }

    private StaffAttendanceDto mapToDto(StaffAttendance attendance, Long staffId) {
        StaffAttendanceDto dto = new StaffAttendanceDto();
        dto.setId(attendance.getId());
        dto.setStaffId(staffId);
        dto.setAttendanceDate(attendance.getDate());
        dto.setStatus(attendance.getStatus() == null ? null : attendance.getStatus().name());
        dto.setRemark(attendance.getRemarks());
        return dto;
    }
}