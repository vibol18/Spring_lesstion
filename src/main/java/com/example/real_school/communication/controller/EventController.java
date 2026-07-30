package com.example.real_school.communication.controller;

import com.example.real_school.communication.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    // TODO: Implement event endpoints
}
