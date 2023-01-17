package rs.ac.uns.acs.smpuos.reservations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
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
        Object reservation1 = restTemplate.getForObject(
                "http://127.0.0.1:9000/users-service/user-info?id=63c4a1222bd10c59173991d5", Object.class, 2);

        System.out.println("S");
        //reservationService.insert(reservation1);
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
