package com.devsenior.lcarvajal.reservation.dto;

import com.devsenior.lcarvajal.reservation.entity.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Response DTO for a reservation.
 *
 * @param id           reservation identifier
 * @param customerName name of the customer
 * @param date         reservation date
 * @param time         reservation time
 * @param service      service requested
 * @param status       current status
 */
public record ReservationResponse(
        Long id,
        String customerName,
        LocalDate date,
        LocalTime time,
        String service,
        ReservationStatus status
) {}
