package com.talha.ticketing.mapper;

import com.talha.ticketing.dto.event.EventResponseDTO;
import com.talha.ticketing.dto.reservation.ReservationResponseDTO;
import com.talha.ticketing.dto.user.UserResponseDTO;
import com.talha.ticketing.entity.Event;
import com.talha.ticketing.entity.Reservation;
import com.talha.ticketing.entity.User;
import com.talha.ticketing.enums.ReservationStatus;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMapper {

    private final UserMapper userMapper;
    private final EventMapper eventMapper;

    public ReservationMapper(UserMapper userMapper, EventMapper eventMapper) {
        this.userMapper = userMapper;
        this.eventMapper = eventMapper;
    }

    public ReservationResponseDTO toResponseDTO(Reservation reservation) {
        if (reservation == null) {
            return null;
        }

        UserResponseDTO user = userMapper.toResponseDTO(reservation.getUser());
        EventResponseDTO event = eventMapper.toResponseDTO(reservation.getEvent());

        return new ReservationResponseDTO(
                reservation.getId(),
                user,
                event,
                reservation.getCreatedAt()
        );
    }

    public Reservation toEntity(User user, Event event) {
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setEvent(event);
        reservation.setStatus(ReservationStatus.RESERVED);
        return reservation;
    }

    public List<ReservationResponseDTO> toResponseDTOList(List<Reservation> reservations) {
        if (reservations == null || reservations.isEmpty()) {
            return Collections.emptyList();
        }

        return reservations.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}

