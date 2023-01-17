package rs.ac.uns.acs.smpuos.reservations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.smpuos.reservations.model.Reservation;
import rs.ac.uns.acs.smpuos.reservations.service.IReservationService;

import java.util.Optional;

@RestController
public class ReservationController {
    @Autowired
    private IReservationService reservationService;

    //rezervacija
    @RequestMapping(value = "/reserve", method = RequestMethod.POST)
    public void addReservation(@RequestBody Reservation reservation) {
        reservationService.insert(reservation);
    }

    //otkazivanje-rezervacije
    @DeleteMapping("/cancel")
    public void deleteReservation(@RequestParam("id") String id) {
        reservationService.deleteById(id);
    }

    //pregled-rezervacije
    @RequestMapping(value = "/review", method = RequestMethod.GET)
    public Optional<Reservation> getReservation(
            @RequestParam(name = "passengerId", required = true) String passengerId) {
        return reservationService.findByPassengerId(passengerId);
    }
}
