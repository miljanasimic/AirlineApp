package rs.ac.uns.acs.smpuos.flights.service;

import org.neo4j.driver.internal.value.RelationshipValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.smpuos.flights.model.Flight;
import rs.ac.uns.acs.smpuos.flights.repository.FlightRepository;

import java.time.ZonedDateTime;
import java.util.*;

@Service
public class FlightService implements IFlightService{

    @Autowired
    FlightRepository flightRepository;

    @Override
    public List<Flight> getOneWayFlightsByAirportsAndDate(String srcAirport, String dstAirport, String startDate, Integer passengersNum) {
        List<RelationshipValue> response = flightRepository.findFlightsByCriteria(srcAirport, dstAirport, startDate, passengersNum);
        List<Flight> flights = new ArrayList<>();
        try {
            Class c = Class.forName(Flight.class.getName());
            for (RelationshipValue el: response) {
                Map m = el.asMap();
                ZonedDateTime zdt = (ZonedDateTime) m.get("date");
                flights.add(new Flight(Date.from(zdt.toInstant()),
                        Long.valueOf((Long)m.get("baggage")).intValue(),
                        Long.valueOf((Long) m.get("seatsRemaining")).intValue(),
                        Long.valueOf((Long)m.get("price")).intValue(), srcAirport, dstAirport));
            }
            return flights;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean checkAvailability(Long flightId, Integer seatsRemainining) {;
        return flightRepository.findSeatsRemaining(flightId, seatsRemainining);
    }
}
