package rs.ac.uns.acs.smpuos.flights.model;


import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node
public class Airport {
    @Id
    private final String code;

    @Property("country")
    private final String country;

    @Property("city")
    private final String cityWithDetails;
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
