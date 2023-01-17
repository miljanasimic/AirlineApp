package rs.ac.uns.acs.smpuos.flights.model;


import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.*;

@Node
public class Airport {
    @Id
    private final String code;

    @Property("country")
    private final String country;

    @Property("city")
    private final String cityWithDetails;


//    public List<Airport> getDirectAirports() {
//        return directAirports;
//    }
//
//    @Relationship(type = "FLIGHT", direction =  Relationship.Direction.OUTGOING)
//    private List<Airport> directAirports = new ArrayList<>();
    public Airport(String code, String country, String cityWithDetails) {
        this.code = code;
        this.country = country;
        this.cityWithDetails = cityWithDetails;
    }
    public String getCode() {
        return code;
    }

    public String getCountry() {
        return country;
    }

    public String getCityWithDetails() {
        return cityWithDetails;
    }
}
