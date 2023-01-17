package rs.ac.uns.acs.smpuos.flights.service;

import rs.ac.uns.acs.smpuos.flights.model.Flight;

import java.util.List;
import java.util.Optional;

public interface IFlightService {

    List<Flight> getOneWayFlightsByAirportsAndDate(String srcAirport, String dstAirport, String startDate, Integer passengersNum);
    Boolean checkAvailability(Long flightId, Integer seatsRemainining);
}
