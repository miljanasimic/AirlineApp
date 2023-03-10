package rs.ac.uns.acs.smpuos.reservations.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import rs.ac.uns.acs.smpuos.reservations.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationsRepository extends MongoRepository<Reservation, String> {

    List<Reservation> findByPassengerId(String passengerId);
    Optional<Reservation> findByFlightId(Long flightId);
}
