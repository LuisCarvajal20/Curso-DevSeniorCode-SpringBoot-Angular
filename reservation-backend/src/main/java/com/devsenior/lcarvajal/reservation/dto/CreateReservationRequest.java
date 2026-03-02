package com.devsenior.lcarvajal.reservation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Request body for creating a new reservation.
 *
 * @param customerName name of the customer
 * @param date         reservation date
 * @param time         reservation time
 * @param service      service requested
 */
public record CreateReservationRequest(
        @NotBlank @Size(max = 255) String customerName,
        @NotNull LocalDate date,
        @NotNull LocalTime time,
        @NotBlank @Size(max = 255) String service
) {}
