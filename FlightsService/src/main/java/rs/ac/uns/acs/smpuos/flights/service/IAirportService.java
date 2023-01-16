package rs.ac.uns.acs.smpuos.flights.service;

import rs.ac.uns.acs.smpuos.flights.model.Airport;
import java.util.List;
import java.time.LocalDateTime;

public interface IAirportService {

    List<Airport> getAirportsInCountry(String country);
    List<String> getAllCountries();

    List<Airport> getDirectAirports(String fromAirport);

    List<LocalDateTime> getPossibleFlightsDates(String from, String to);
}
