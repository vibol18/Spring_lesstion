package com.example.real_school.communication.service;

import com.example.real_school.communication.dto.MessageDto;
import java.util.List;

public interface MessageService {
    List<MessageDto> getAllMessages();
    MessageDto getMessageById(Long id);
    MessageDto sendMessage(MessageDto dto);
    void deleteMessage(Long id);
    List<MessageDto> getInbox(String receiverName);
}
