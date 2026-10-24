package com.example.real_school.communication.service;

import com.example.real_school.communication.dto.LeaveApplicationDto;
import java.util.List;

public interface LeaveApplicationService {
    List<LeaveApplicationDto> getAllLeaveApplications();
    LeaveApplicationDto getLeaveApplicationById(Long id);
    LeaveApplicationDto applyForLeave(LeaveApplicationDto dto);
    LeaveApplicationDto updateStatus(Long id, String status);
}
