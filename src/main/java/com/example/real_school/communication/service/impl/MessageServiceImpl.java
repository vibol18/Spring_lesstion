package com.example.real_school.communication.service.impl;

import com.example.real_school.communication.dto.MessageDto;
import com.example.real_school.communication.entity.Message;
import com.example.real_school.communication.repository.MessageRepository;
import com.example.real_school.communication.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public List<MessageDto> getAllMessages() {
        return messageRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public MessageDto getMessageById(Long id) {
        return mapToDto(messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found with id: " + id)));
    }

    @Override
    public MessageDto sendMessage(MessageDto dto) {
        Message msg = new Message();
        msg.setSenderName(dto.getSenderName());
        msg.setReceiverName(dto.getReceiverName());
        msg.setSubject(dto.getSubject());
        msg.setBody(dto.getBody());
        msg.setIsRead(false);
        msg.setSentAt(LocalDateTime.now());
        return mapToDto(messageRepository.save(msg));
    }

    @Override
    public void deleteMessage(Long id) {
        if (!messageRepository.existsById(id)) throw new RuntimeException("Message not found with id: " + id);
        messageRepository.deleteById(id);
    }

    @Override
    public List<MessageDto> getInbox(String receiverName) {
        return messageRepository.findAll().stream()
                .filter(m -> receiverName.equalsIgnoreCase(m.getReceiverName()))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private MessageDto mapToDto(Message m) {
        MessageDto dto = new MessageDto();
        dto.setId(m.getId());
        dto.setSenderName(m.getSenderName());
        dto.setReceiverName(m.getReceiverName());
        dto.setSubject(m.getSubject());
        dto.setBody(m.getBody());
        dto.setIsRead(m.getIsRead());
        dto.setSentAt(m.getSentAt());
        return dto;
    }
}
