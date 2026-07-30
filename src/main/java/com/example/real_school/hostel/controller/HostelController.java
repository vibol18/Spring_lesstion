package com.example.real_school.hostel.controller;

import com.example.real_school.hostel.service.HostelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hostels")
@RequiredArgsConstructor
public class HostelController {
    private final HostelService hostelService;
}
