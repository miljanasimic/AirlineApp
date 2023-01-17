package rs.ac.uns.acs.smpuos.flights.controller;

import org.neo4j.driver.internal.value.DateTimeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.smpuos.flights.model.Airport;
import rs.ac.uns.acs.smpuos.flights.model.Flight;
import rs.ac.uns.acs.smpuos.flights.service.IAirportService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/airport")
public class AirportController {

    @Autowired
    private IAirportService airportService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "test";
    }
    @GetMapping(value = "/byCountry")
    public List<Airport> getAllByCountryName(@RequestParam String country)
    {
        return airportService.getAirportsInCountry(country);
    }
    @GetMapping(value = "/countries")
    public List<String> getCountriesOffer(){
        return airportService.getAllCountries();
    }
    @GetMapping(value = "/directAirports")
    public List<Airport> getDirectAirports(@RequestParam String startAirportCode){
        return airportService.getDirectAirports(startAirportCode);
    }

    @GetMapping(value = "/allPossibleFlightsDates")
    public List<LocalDateTime> getFlightsDates(@RequestParam String srcAirportCode, @RequestParam String dstAirportCode){
        return airportService.getPossibleFlightsDates(srcAirportCode, dstAirportCode);
    }

//    @GetMapping(value = "/oneWayFlightsByCriteria")
//    public List<Flight> getOneWayFlightsBySearch(@RequestParam String srcAirportCode, @RequestParam String dstAirportCode,
//                                           @RequestParam String date, @RequestParam Integer passengerNumber){
//        return airportService.getOneWayFlightsByAirportsAndDate(srcAirportCode, dstAirportCode, date, passengerNumber);
//    }



}
