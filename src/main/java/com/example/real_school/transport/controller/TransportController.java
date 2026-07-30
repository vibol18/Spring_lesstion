package com.example.real_school.transport.controller;

import com.example.real_school.transport.service.TransportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transport")
@RequiredArgsConstructor
public class TransportController {
    private final TransportService transportService;

    // TODO: Implement transport endpoints
}
