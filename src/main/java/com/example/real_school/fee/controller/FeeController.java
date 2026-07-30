package com.example.real_school.fee.controller;

import com.example.real_school.fee.service.FeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fees")
@RequiredArgsConstructor
public class FeeController {
    private final FeeService feeService;
}
