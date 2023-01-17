package rs.ac.uns.acs.smpuos.reservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.smpuos.reservations.model.Reservation;
import rs.ac.uns.acs.smpuos.reservations.repository.ReservationsRepository;

import java.util.Optional;

@Service
public class ReservationService implements IReservationService{

    @Autowired
    ReservationsRepository reservationsRepository;

    public void insert(Reservation reservation) {
        reservationsRepository.insert(reservation);
    }


    public Optional<Reservation> deleteById(String id) {
        reservationsRepository.deleteById(id);
        return null;
    }

    public Optional<Reservation> findByPassengerId(String passengerId){
        return reservationsRepository.findByPassengerId(passengerId);
    }

    public Optional<Reservation> findByFlightId(String flightId){
        return reservationsRepository.findByFlightId(flightId);
    }
}
