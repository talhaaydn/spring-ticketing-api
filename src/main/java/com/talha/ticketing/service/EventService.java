package com.talha.ticketing.service;

import com.talha.ticketing.dto.event.EventRequestDTO;
import com.talha.ticketing.dto.event.EventResponseDTO;
import com.talha.ticketing.entity.Event;
import com.talha.ticketing.mapper.EventMapper;
import com.talha.ticketing.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;


    @Autowired
    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public List<EventResponseDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return eventMapper.toResponseDTOList(events);
    }

    public EventResponseDTO getOneEvent(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new IllegalArgumentException("Event not found with id: " + id);
        }
        return eventMapper.toResponseDTO(event.get());
    }

    public EventResponseDTO createEvent(EventRequestDTO eventRequestDTO) {
        Event event = eventMapper.toEntity(eventRequestDTO);

        return eventMapper.toResponseDTO(
            eventRepository.save(event)
        );
    }
}

