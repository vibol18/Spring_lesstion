package com.example.real_school.communication.service;

import com.example.real_school.communication.dto.EventDto;
import java.util.List;

public interface EventService {
    List<EventDto> getAllEvents();
    EventDto getEventById(Long id);
    EventDto createEvent(EventDto dto);
    EventDto updateEvent(Long id, EventDto dto);
    void deleteEvent(Long id);
}
