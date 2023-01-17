package rs.ac.uns.acs.smpuos.flights.controller;

import org.neo4j.driver.internal.value.RelationshipValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.smpuos.flights.model.Airport;
import rs.ac.uns.acs.smpuos.flights.model.Flight;
import rs.ac.uns.acs.smpuos.flights.service.IAirportService;
import rs.ac.uns.acs.smpuos.flights.service.IFlightService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private IFlightService flightService;

    @GetMapping(value = "/oneWayFlightsByCriteria")
    public List<Flight> getOneWayFlightsBySearch(@RequestParam String srcAirportCode, @RequestParam String dstAirportCode,
                                                            @RequestParam String date, @RequestParam Integer passengerNumber){
        return flightService.getOneWayFlightsByAirportsAndDate(srcAirportCode, dstAirportCode, date, passengerNumber);
    }

    @PatchMapping(value = "/checkAvailability")
    public ResponseEntity checkAvailability(@RequestParam Long flightId, @RequestParam Integer passengerNumber){
        Boolean result = flightService.checkAvailability(flightId, passengerNumber);
        if (result!=null && result)
            return new ResponseEntity(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
