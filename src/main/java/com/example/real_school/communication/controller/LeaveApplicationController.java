package com.example.real_school.communication.controller;

import com.example.real_school.communication.service.LeaveApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leave-applications")
@RequiredArgsConstructor
public class LeaveApplicationController {
    private final LeaveApplicationService leaveApplicationService;

    // TODO: Implement leave application endpoints
}
