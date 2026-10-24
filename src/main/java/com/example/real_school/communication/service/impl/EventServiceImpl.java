package com.example.real_school.communication.service.impl;

import com.example.real_school.communication.dto.EventDto;
import com.example.real_school.communication.entity.Event;
import com.example.real_school.communication.repository.EventRepository;
import com.example.real_school.communication.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public List<EventDto> getAllEvents() {
        return eventRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public EventDto getEventById(Long id) {
        return mapToDto(eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id)));
    }

    @Override
    public EventDto createEvent(EventDto dto) {
        Event event = new Event();
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setEventDate(dto.getEventDate());
        event.setLocation(dto.getLocation());
        event.setOrganizer(dto.getOrganizer());
        return mapToDto(eventRepository.save(event));
    }

    @Override
    public EventDto updateEvent(Long id, EventDto dto) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setEventDate(dto.getEventDate());
        event.setLocation(dto.getLocation());
        event.setOrganizer(dto.getOrganizer());
        return mapToDto(eventRepository.save(event));
    }

    @Override
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) throw new RuntimeException("Event not found with id: " + id);
        eventRepository.deleteById(id);
    }

    private EventDto mapToDto(Event e) {
        EventDto dto = new EventDto();
        dto.setId(e.getId());
        dto.setTitle(e.getTitle());
        dto.setDescription(e.getDescription());
        dto.setEventDate(e.getEventDate());
        dto.setLocation(e.getLocation());
        dto.setOrganizer(e.getOrganizer());
        dto.setCreatedAt(e.getCreatedAt());
        return dto;
    }
}
