package com.talha.ticketing.service;

import com.talha.ticketing.dto.reservation.ReservationRequestDTO;
import com.talha.ticketing.dto.reservation.ReservationResponseDTO;
import com.talha.ticketing.entity.Event;
import com.talha.ticketing.entity.Reservation;
import com.talha.ticketing.entity.User;
import com.talha.ticketing.exception.InsufficientQuotaException;
import com.talha.ticketing.exception.InvalidDateException;
import com.talha.ticketing.exception.ResourceNotFoundException;
import com.talha.ticketing.mapper.ReservationMapper;
import com.talha.ticketing.repository.EventRepository;
import com.talha.ticketing.repository.ReservationRepository;
import com.talha.ticketing.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class ReservationService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public ReservationService(
            UserRepository userRepository,
            EventRepository eventRepository,
            ReservationRepository reservationRepository,
            ReservationMapper reservationMapper
    ) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    public ReservationResponseDTO createReservation(ReservationRequestDTO reservationRequestDTO) {
        User user = userRepository.findById(reservationRequestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", reservationRequestDTO.getUserId()));

        Event event = eventRepository.findByIdWithLock(reservationRequestDTO.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Event", reservationRequestDTO.getEventId()));

        if (event.getEventDate().isBefore(LocalDateTime.now())) {
            throw new InvalidDateException("Cannot create reservation. Event date is in the past.");
        }

        if (event.getQuota() <= 0) {
            throw new InsufficientQuotaException(event.getId());
        }

        int updatedRows = eventRepository.decrementQuota(event.getId());
        
        if (updatedRows == 0) {
            throw new InsufficientQuotaException("Event quota exhausted during reservation. Please try again.");
        }

        Reservation reservation = reservationMapper.toEntity(user, event);

        reservationRepository.save(reservation);

        return reservationMapper.toResponseDTO(reservation);
    }

}

