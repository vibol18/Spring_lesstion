package com.example.real_school.communication.controller;

import com.example.real_school.communication.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    // TODO: Implement notice endpoints
}
