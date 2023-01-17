package rs.ac.uns.acs.smpuos.flights.service;

import org.neo4j.driver.Value;
import org.neo4j.driver.internal.value.ListValue;
import org.neo4j.driver.internal.value.MapValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.smpuos.flights.model.Airport;
import rs.ac.uns.acs.smpuos.flights.model.Flight;
import rs.ac.uns.acs.smpuos.flights.repository.AirportRepository;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AirportService implements IAirportService{

    @Autowired
    AirportRepository airportRepository;
    @Override
    public List<Airport> getAirportsInCountry(String country) {
        return airportRepository.findByCountryOrderByCityWithDetailsAsc(country);
    }

    @Override
    public List<String> getAllCountries() {
        return airportRepository.findAirportCountries();
    }

    @Override
    public List<Airport> getDirectAirports(String fromAirport) {
        return airportRepository.findDirectAirports(fromAirport);
    }

    @Override
    public List<LocalDateTime> getPossibleFlightsDates(String from, String to) {
        List<ListValue> response = airportRepository.findPossibleFlightsDates(from, to);
        List<LocalDateTime> allFlightsDates = new ArrayList<>();
        ListValue datesResponse = response.get(0);
        for (Value flightDate : datesResponse.values()) {
            LocalDateTime newDate = LocalDateTime.of(flightDate.get("year").asInt(),
                    flightDate.get("month").asInt(),
                    flightDate.get("day").asInt(),
                    flightDate.get("hour").asInt(),
                    flightDate.get("minute").asInt(),
                    0);
            allFlightsDates.add(newDate);
        }
        return allFlightsDates;
    }

}
