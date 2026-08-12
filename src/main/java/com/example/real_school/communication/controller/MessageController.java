package com.example.real_school.communication.controller;

import com.example.real_school.communication.dto.MessageDto;
import com.example.real_school.communication.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<List<MessageDto>> getAll() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @PostMapping
    public ResponseEntity<MessageDto> send(@RequestBody MessageDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.sendMessage(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(messageService.getMessageById(id));
    }

    @GetMapping("/inbox")
    public ResponseEntity<List<MessageDto>> getInbox(@RequestParam String receiver) {
        return ResponseEntity.ok(messageService.getInbox(receiver));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}
