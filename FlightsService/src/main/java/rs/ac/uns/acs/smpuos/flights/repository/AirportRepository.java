package rs.ac.uns.acs.smpuos.flights.repository;

import org.neo4j.driver.internal.value.DateTimeValue;
import org.neo4j.driver.internal.value.ListValue;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import rs.ac.uns.acs.smpuos.flights.model.Airport;


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
            "WHERE src.code=$srcAirportCode AND dst.code=$dstAirportCode\n" +
            "WITH f.date as date\n" +
            "RETURN collect({year:date.year,month:date.month,day:date.day,hour:date.hour, minute:date.minute}) as dates")
    List<ListValue> findPossibleFlightsDates(String srcAirportCode, String dstAirportCode);
}
