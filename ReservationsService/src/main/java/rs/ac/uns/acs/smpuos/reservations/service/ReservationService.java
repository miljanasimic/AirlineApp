package rs.ac.uns.acs.smpuos.reservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.smpuos.reservations.model.Reservation;
import rs.ac.uns.acs.smpuos.reservations.repository.ReservationsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService implements IReservationService{

    @Autowired
    ReservationsRepository reservationsRepository;

    public void insert(Reservation reservation) {
        reservationsRepository.insert(reservation);
    }

    public Optional<Reservation> findById(String id){

        return reservationsRepository.findById(id);
    }


    public void deleteById(String id) {
        reservationsRepository.deleteById(id);
    }

    public List<Reservation> findByPassengerId(String passengerId){
        return reservationsRepository.findByPassengerId(passengerId);
    }

    public Optional<Reservation> findByFlightId(Long flightId){
        return reservationsRepository.findByFlightId(flightId);
    }
}
