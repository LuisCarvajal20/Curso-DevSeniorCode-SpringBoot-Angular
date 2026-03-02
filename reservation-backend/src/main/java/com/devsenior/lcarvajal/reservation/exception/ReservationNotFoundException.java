package com.devsenior.lcarvajal.reservation.exception;

/**
 * Thrown when a reservation is not found by its identifier.
 */
public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException(String message) {
        super(message);
    }
}
