package com.example.real_school.communication.controller;

import com.example.real_school.communication.dto.NoticeDto;
import com.example.real_school.communication.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public ResponseEntity<List<NoticeDto>> getAll() {
        return ResponseEntity.ok(noticeService.getAllNotices());
    }

    @PostMapping
    public ResponseEntity<NoticeDto> create(@RequestBody NoticeDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noticeService.createNotice(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticeDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(noticeService.getNoticeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoticeDto> update(@PathVariable Long id, @RequestBody NoticeDto dto) {
        return ResponseEntity.ok(noticeService.updateNotice(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return ResponseEntity.noContent().build();
    }
}
