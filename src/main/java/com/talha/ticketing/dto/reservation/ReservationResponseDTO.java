package com.talha.ticketing.dto.reservation;

import com.talha.ticketing.dto.event.EventResponseDTO;
import com.talha.ticketing.dto.user.UserResponseDTO;

import java.time.LocalDateTime;

public class ReservationResponseDTO {

    private Long id;
    private UserResponseDTO user;
    private EventResponseDTO event;
    private LocalDateTime createdAt;

    public ReservationResponseDTO(Long id, UserResponseDTO user, EventResponseDTO event, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.event = event;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    public EventResponseDTO getEvent() {
        return event;
    }

    public void setEvent(EventResponseDTO event) {
        this.event = event;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

