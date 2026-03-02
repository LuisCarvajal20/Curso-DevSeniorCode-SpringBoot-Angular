package com.devsenior.lcarvajal.reservation.exception;

/**
 * Thrown when attempting to cancel a reservation that is already cancelled.
 */
public class ReservationAlreadyCancelledException extends RuntimeException {

    public ReservationAlreadyCancelledException(String message) {
        super(message);
    }
}
