package rs.ac.uns.acs.smpuos.reservations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
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
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity responseEntity = restTemplate.patchForObject(
                "http://localhost:9000/flights-service/flight/checkAvailability/?flightId="+reservation.getFlightId()
                        + "&passengerNumber="+reservation.getPassengerNumber(), null , ResponseEntity.class, 2);

        if (responseEntity.getStatusCode() == HttpStatus.OK)
            reservationService.insert(reservation);
        else {
            responseEntity.getStatusCode();
        }
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
