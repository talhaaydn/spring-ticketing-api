package com.talha.ticketing.repository;

import com.talha.ticketing.entity.Event;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT e FROM Event e WHERE e.id = :eventId")
    Optional<Event> findByIdWithLock(@Param("eventId") Long eventId);

    @Modifying
    @Query("UPDATE Event e SET e.quota = e.quota - 1 WHERE e.id = :eventId AND e.quota > 0")
    int decrementQuota(@Param("eventId") Long eventId);
}

