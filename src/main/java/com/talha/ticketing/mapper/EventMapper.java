package com.talha.ticketing.mapper;

import com.talha.ticketing.dto.event.EventRequestDTO;
import com.talha.ticketing.dto.event.EventResponseDTO;
import com.talha.ticketing.entity.Event;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventMapper {

    public EventResponseDTO toResponseDTO(Event event) {
        if (event == null) {
            return null;
        }

        return new EventResponseDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getEventDate(),
                event.getQuota()
        );
    }

    public Event toEntity(EventRequestDTO dto) {
        Event event = new Event();
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setEventDate(dto.getEventDate());
        event.setQuota(dto.getQuota());
        return event;
    }

    public List<EventResponseDTO> toResponseDTOList(List<Event> events) {
        if (events == null || events.isEmpty()) {
            return Collections.emptyList();
        }

        return events.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}

