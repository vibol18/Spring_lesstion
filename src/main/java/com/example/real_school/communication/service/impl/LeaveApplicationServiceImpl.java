package com.example.real_school.communication.service.impl;

import com.example.real_school.communication.dto.LeaveApplicationDto;
import com.example.real_school.communication.entity.LeaveApplication;
import com.example.real_school.communication.repository.LeaveApplicationRepository;
import com.example.real_school.communication.service.LeaveApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LeaveApplicationServiceImpl implements LeaveApplicationService {

    private final LeaveApplicationRepository leaveApplicationRepository;

    @Override
    public List<LeaveApplicationDto> getAllLeaveApplications() {
        return leaveApplicationRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public LeaveApplicationDto getLeaveApplicationById(Long id) {
        return mapToDto(leaveApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave application not found with id: " + id)));
    }

    @Override
    public LeaveApplicationDto applyForLeave(LeaveApplicationDto dto) {
        LeaveApplication app = new LeaveApplication();
        app.setApplicantName(dto.getApplicantName());
        app.setReason(dto.getReason());
        app.setStartDate(dto.getStartDate());
        app.setEndDate(dto.getEndDate());
        app.setStatus(LeaveApplication.LeaveStatus.PENDING);
        app.setAppliedAt(LocalDateTime.now());
        return mapToDto(leaveApplicationRepository.save(app));
    }

    @Override
    public LeaveApplicationDto updateStatus(Long id, String status) {
        LeaveApplication app = leaveApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave application not found with id: " + id));
        app.setStatus(LeaveApplication.LeaveStatus.valueOf(status.toUpperCase()));
        return mapToDto(leaveApplicationRepository.save(app));
    }

    private LeaveApplicationDto mapToDto(LeaveApplication a) {
        LeaveApplicationDto dto = new LeaveApplicationDto();
        dto.setId(a.getId());
        dto.setApplicantName(a.getApplicantName());
        dto.setReason(a.getReason());
        dto.setStartDate(a.getStartDate());
        dto.setEndDate(a.getEndDate());
        dto.setStatus(a.getStatus() != null ? a.getStatus().name() : null);
        dto.setAppliedAt(a.getAppliedAt());
        return dto;
    }
}
