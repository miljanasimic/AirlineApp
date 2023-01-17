package rs.ac.uns.acs.smpuos.flights.repository;

import org.neo4j.driver.internal.value.ListValue;
import org.neo4j.driver.internal.value.RelationshipValue;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import rs.ac.uns.acs.smpuos.flights.model.Airport;
import rs.ac.uns.acs.smpuos.flights.model.Flight;

import java.util.List;
import java.util.Map;


public interface FlightRepository extends Neo4jRepository<Flight, Long> {

    @Query("MATCH p=(a:Airport{code: $srcAirportCode})-[f:FLIGHT]->(dst:Airport{code:$dstAirportCode})\n" +
            "WHERE date(f.date)=date($flightDate) AND f.seatsRemaining>=$passengersNum\n" +
            "RETURN f as flight")
    List<RelationshipValue> findFlightsByCriteria(String srcAirportCode, String dstAirportCode, String flightDate, Integer passengersNum);
}
