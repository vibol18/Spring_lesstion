package com.example.real_school.fee.controller;

import com.example.real_school.fee.service.FeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/fees")
@RequiredArgsConstructor
public class FeeController {
    private final FeeService feeService;
    // Fee summary endpoints can be added as FeeService is expanded
}
