package rs.ac.uns.acs.smpuos.reservations.controller;

import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import rs.ac.uns.acs.smpuos.reservations.model.Reservation;
import rs.ac.uns.acs.smpuos.reservations.service.IReservationService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private IReservationService reservationService;

    //rezervacija
    @RequestMapping(value = "/reserve", method = RequestMethod.POST)
    public String addReservation(@RequestBody Reservation reservation) {
        try{
            String result = null;
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
            RestTemplate restTemplate = restTemplateBuilder.build();
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault()));
            ResponseEntity responseEntity = restTemplate.exchange(
                    "http://localhost:9000/flights-service/flight/checkAvailability/?flightId=" + reservation.getFlightId()
                            + "&passengerNumber=" + reservation.getPassengerNumber(), HttpMethod.PATCH, null, ResponseEntity.class, 2);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                reservationService.insert(reservation);
                result = "Rezervacija je uspesna.";
            } else {
                responseEntity.getStatusCode();
                result = "Rezervacija nije uspela. Pokusajte ponovo";
            }
            return result;
        } catch (Exception e){
            return "Rezervacija nije uspela. Pokusajte ponovo";
        }
    }

    //otkazivanje-rezervacije
    @DeleteMapping("/cancel")
    public ResponseEntity<String> deleteReservation(@RequestParam("id") String id) {
        Optional<Reservation> reservationData = reservationService.findById(id);
        if(reservationData.isPresent())
        {
            Date date = new Date();
            Reservation reservation1 = reservationData.get();
            if (reservation1.getDate().after(date)) {
                RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
                RestTemplate restTemplate = restTemplateBuilder.build();
                restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault()));
                ResponseEntity responseEntity = restTemplate.exchange(
                        "http://localhost:9000/flights-service/flight/returnTicket/?flightId=" + reservation1.getFlightId()
                                + "&passengerNumber=" + reservation1.getPassengerNumber(), HttpMethod.PATCH, null, ResponseEntity.class, 2);
                reservationService.deleteById(id);
                return new ResponseEntity<>("Rezervacija uspesno otkazana.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Nemoguce otkazati rezervaciju.", HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>("Rezervacija ne postoji.", HttpStatus.NOT_FOUND);
        }
    }



    //pregled-rezervacije
    @RequestMapping(value = "/review", method = RequestMethod.GET)
    public ResponseEntity<List<Reservation>> getReservation(
            @RequestParam(name = "passengerId", required = true) String passengerId) {

        try{
            List<Reservation> reservations;
            reservations=reservationService.findByPassengerId(passengerId);
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


}
