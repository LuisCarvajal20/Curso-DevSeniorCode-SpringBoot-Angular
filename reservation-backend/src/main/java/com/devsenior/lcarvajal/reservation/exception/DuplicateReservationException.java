package com.devsenior.lcarvajal.reservation.exception;

/**
 * Thrown when attempting to create a reservation for a date and time
 * that already has an active reservation.
 */
public class DuplicateReservationException extends RuntimeException {

    public DuplicateReservationException(String message) {
        super(message);
    }
}
