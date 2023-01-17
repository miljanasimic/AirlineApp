package rs.ac.uns.acs.smpuos.reservations.service;

import rs.ac.uns.acs.smpuos.reservations.model.Reservation;

import java.util.Optional;

public interface IReservationService {
    void insert(Reservation reservation);
    Optional<Reservation> deleteById(String id);
    Optional<Reservation> findByPassengerId(String passengerId);
}
