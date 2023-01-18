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

    @GetMapping(value = "/recommendedRoutesFlights")
    public List<Flight> getFlightsByRoutes(@RequestParam List<String> routes, @RequestParam String date, @RequestParam Integer passengerNumber){
        return flightService.findRecommendedFlightsByCriteria(routes, date, passengerNumber);
    }

    @PatchMapping(value = "/checkAvailability")
    public @ResponseBody ResponseEntity checkAvailability(@RequestParam Long flightId, @RequestParam Integer passengerNumber){
        Boolean result = flightService.checkAvailability(flightId, passengerNumber);
        if (result!=null && result)
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PatchMapping(value = "/returnTicket")
    public @ResponseBody ResponseEntity returnTicket (@RequestParam Long flightId, @RequestParam Integer passengerNumber){
        Boolean result = flightService.returnTicket(flightId, passengerNumber);
        if (result=true) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}
