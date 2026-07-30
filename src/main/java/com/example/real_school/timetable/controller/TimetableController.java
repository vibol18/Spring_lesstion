package com.example.real_school.timetable.controller;

import com.example.real_school.timetable.service.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/timetables")
@RequiredArgsConstructor
public class TimetableController {
    private final TimetableService timetableService;
}
