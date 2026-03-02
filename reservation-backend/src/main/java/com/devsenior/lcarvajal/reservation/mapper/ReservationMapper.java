package com.devsenior.lcarvajal.reservation.mapper;

import com.devsenior.lcarvajal.reservation.dto.CreateReservationRequest;
import com.devsenior.lcarvajal.reservation.dto.ReservationResponse;
import com.devsenior.lcarvajal.reservation.entity.Reservation;
import com.devsenior.lcarvajal.reservation.entity.ReservationStatus;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between Reservation entity and DTOs.
 */
@Component
public class ReservationMapper {

    /**
     * Converts a create request DTO to a new Reservation entity.
     *
     * @param request the creation request (must not be null)
     * @return a new Reservation with status ACTIVA, id is null
     */
    public Reservation toEntity(CreateReservationRequest request) {
        Reservation reservation = new Reservation();
        reservation.setCustomerName(request.customerName());
        reservation.setDate(request.date());
        reservation.setTime(request.time());
        reservation.setService(request.service());
        reservation.setStatus(ReservationStatus.ACTIVA);
        return reservation;
    }

    /**
     * Converts a Reservation entity to a response DTO.
     *
     * @param reservation the entity (must not be null)
     * @return the response DTO
     */
    public ReservationResponse toResponse(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getCustomerName(),
                reservation.getDate(),
                reservation.getTime(),
                reservation.getService(),
                reservation.getStatus());
    }
}
