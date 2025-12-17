package com.talha.ticketing.exception;

public class InsufficientQuotaException extends RuntimeException {
    
    public InsufficientQuotaException(String message) {
        super(message);
    }
    
    public InsufficientQuotaException(Long eventId) {
        super(String.format("Event with id %d has no available quota", eventId));
    }
}

