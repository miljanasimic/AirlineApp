package rs.ac.uns.acs.smpuos.flights.service;

import org.neo4j.driver.internal.value.RelationshipValue;
import rs.ac.uns.acs.smpuos.flights.model.Airport;
import rs.ac.uns.acs.smpuos.flights.model.Flight;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface IFlightService {


    List<Flight> getOneWayFlightsByAirportsAndDate(String srcAirport,
                                                   String dstAirport,
                                                   String startDate,
                                                   Integer passengersNum);

    List<Flight> findRecommendedFlightsByCriteria(List<String> routes, String flightDate, Integer passengersNum);
}
