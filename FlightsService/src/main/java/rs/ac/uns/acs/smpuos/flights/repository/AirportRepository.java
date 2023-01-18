package rs.ac.uns.acs.smpuos.flights.repository;

import org.neo4j.driver.internal.value.DateTimeValue;
import org.neo4j.driver.internal.value.DateValue;
import org.neo4j.driver.internal.value.ListValue;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import rs.ac.uns.acs.smpuos.flights.model.Airport;
import rs.ac.uns.acs.smpuos.flights.model.Flight;


import java.util.Date;
import java.util.List;


public interface AirportRepository extends Neo4jRepository<Airport, String> {
    List<Airport> findByCountryOrderByCityWithDetailsAsc(String country);

    @Query("MATCH (a:Airport) RETURN DISTINCT a.country;")
    List<String> findAirportCountries();

    @Query("MATCH (src:Airport)-[:FLIGHT]->(dst:Airport)\n" +
            "WHERE src.code=$startAirportCode\n" +
            "RETURN DISTINCT dst")
    List<Airport> findDirectAirports(String startAirportCode);

    @Query("MATCH (src:Airport)-[f:FLIGHT]->(dst:Airport)\n" +
            "WHERE src.code=$srcAirportCode AND dst.code=$dstAirportCode AND f.seatsRemaining>0\n" +
            "WITH date(f.date) as date\n" +
            "RETURN DISTINCT toString(date)")
    List<String> findPossibleFlightsDates(String srcAirportCode, String dstAirportCode);

}
