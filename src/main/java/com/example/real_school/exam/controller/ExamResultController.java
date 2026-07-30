package com.example.real_school.exam.controller;

import com.example.real_school.exam.service.ExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam-results")
@RequiredArgsConstructor
public class ExamResultController {
    private final ExamResultService examResultService;
}
