package com.devsenior.lcarvajal.reservation.repository;

import com.devsenior.lcarvajal.reservation.entity.Reservation;
import com.devsenior.lcarvajal.reservation.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Spring Data JPA repository for {@link Reservation}.
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * Checks whether a reservation already exists for the given date and time.
     *
     * @param date the reservation date (must not be null)
     * @param time the reservation time (must not be null)
     * @return {@code true} if at least one reservation exists, otherwise {@code false}
     */
    boolean existsByDateAndTime(LocalDate date, LocalTime time);

    /**
     * Checks whether a reservation with the given status exists for the given date and time.
     *
     * @param date   the reservation date (must not be null)
     * @param time   the reservation time (must not be null)
     * @param status the reservation status to filter by (must not be null)
     * @return {@code true} if at least one matching reservation exists, otherwise {@code false}
     */
    boolean existsByDateAndTimeAndStatus(LocalDate date, LocalTime time, ReservationStatus status);
}

