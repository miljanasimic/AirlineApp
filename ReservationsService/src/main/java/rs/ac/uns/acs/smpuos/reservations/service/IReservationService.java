package rs.ac.uns.acs.smpuos.reservations.service;

import jdk.dynalink.linker.LinkerServices;
import rs.ac.uns.acs.smpuos.reservations.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationService {
    void insert(Reservation reservation);
    Optional<Reservation> findById(String id);
    void deleteById(String id);
    List<Reservation> findByPassengerId(String passengerId);
    Optional<Reservation> findByFlightId(Long flightID);
}
