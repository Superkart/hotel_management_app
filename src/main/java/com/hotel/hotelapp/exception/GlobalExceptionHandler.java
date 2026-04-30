package com.hotel.hotelapp.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(BookingConflictException.class)
    public ResponseEntity<String> handleBookingConflict(BookingConflictException ex)
    {
        return ResponseEntity.status(409).body(ex.getMessage());
    }

    @ExceptionHandler(ReviewNotAllowedException.class)
    public ResponseEntity<String> handleReviewNotAllowed(ReviewNotAllowedException ex)
    {
        return ResponseEntity.status(403).body(ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex)
    {
        return ResponseEntity.status(409).body("Cannot delete: this record is referenced by existing bookings, rooms, or reviews.");
    }
}
