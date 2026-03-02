package com.devsenior.lcarvajal.reservation.service;

import com.devsenior.lcarvajal.reservation.dto.CreateReservationRequest;
import com.devsenior.lcarvajal.reservation.dto.ReservationResponse;
import com.devsenior.lcarvajal.reservation.entity.Reservation;
import com.devsenior.lcarvajal.reservation.entity.ReservationStatus;
import com.devsenior.lcarvajal.reservation.exception.DuplicateReservationException;
import com.devsenior.lcarvajal.reservation.mapper.ReservationMapper;
import com.devsenior.lcarvajal.reservation.exception.ReservationAlreadyCancelledException;
import com.devsenior.lcarvajal.reservation.exception.ReservationNotFoundException;
import com.devsenior.lcarvajal.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service containing the business logic for reservations.
 */
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationRepository reservationRepository,
                              ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    /**
     * Returns all reservations.
     *
     * @return list of all reservations as DTOs
     */
    @Transactional(readOnly = true)
    public List<ReservationResponse> findAll() {
        return reservationRepository.findAll().stream()
                .map(reservationMapper::toResponse)
                .toList();
    }

    /**
     * Creates a new reservation only if no active reservation exists for the same date and time.
     *
     * @param request the creation request (must be valid and not null)
     * @return the created reservation DTO
     * @throws DuplicateReservationException if an active reservation already exists for the requested date and time
     */
    @Transactional
    public ReservationResponse create(CreateReservationRequest request) {
        if (reservationRepository.existsByDateAndTimeAndStatus(
                request.date(), request.time(), ReservationStatus.ACTIVA)) {
            throw new DuplicateReservationException(
                    "Ya existe una reserva activa para la fecha %s y hora %s".formatted(
                            request.date(), request.time()));
        }

        Reservation reservation = reservationMapper.toEntity(request);
        Reservation saved = reservationRepository.save(reservation);
        return reservationMapper.toResponse(saved);
    }

    /**
     * Cancels a reservation by its identifier.
     *
     * @param id the reservation ID (must not be null)
     * @throws ReservationNotFoundException       if no reservation exists for the given id
     * @throws ReservationAlreadyCancelledException if the reservation is already cancelled
     */
    @Transactional
    public void cancel(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(
                        "No se encontró la reserva con id %d".formatted(id)));

        if (reservation.getStatus() == ReservationStatus.CANCELLED) {
            throw new ReservationAlreadyCancelledException(
                    "La reserva con id %d ya está cancelada".formatted(id));
        }

        reservation.setStatus(ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
    }
}
