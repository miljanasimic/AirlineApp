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

    @Query("MATCH (a:Airport)\n" +
            "WHERE a.code in $routes\n" +
            "WITH collect(a) as airports\n" +
            "UNWIND range(0, size($routes)-2) as index\n" +
            "WITH airports[index] as a1, airports[index+1] as a2\n" +
            "MATCH (a1)-[f1:FLIGHT]->(a2)\n" +
            "WHERE date(f1.date)=date($flightDate) AND f1.seatsRemaining>=$passengersNum\n" +
            "WITH f1, a1, a2\n" +
            "RETURN [f1,a1.code,a2.code];")
    List<ListValue> findRecommendedFlightsByCriteria(List<String> routes, String flightDate, Integer passengersNum);

    @Query("MATCH ()-[f:FLIGHT]->() \n" +
            "WHERE id(f)=$flightId AND f.seatsRemaining-$seatsRemaining>=0\n" +
            "WITH f is not null as res, f\n" +
            "SET f.seatsRemaining=f.seatsRemaining-$seatsRemaining\n" +
            "RETURN res")
    Boolean findSeatsRemaining(Long flightId, Integer seatsRemaining);

}
