package com.example.real_school.notification.controller;

import com.example.real_school.notification.dto.NotificationDto;
import com.example.real_school.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationDto>> getAll() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @PostMapping
    public ResponseEntity<NotificationDto> create(@RequestBody NotificationDto dto) {
        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED)
                .body(notificationService.createNotification(dto));
    }

    @GetMapping("/recipient/{recipientId}")
    public ResponseEntity<List<NotificationDto>> getUnread(
            @PathVariable Long recipientId,
            @RequestParam(defaultValue = "STUDENT") String type) {
        return ResponseEntity.ok(notificationService.getUnreadByRecipient(recipientId, type));
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<NotificationDto> markAsRead(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.markAsRead(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
