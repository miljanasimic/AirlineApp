package rs.ac.uns.acs.smpuos.flights.model;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Date;

@RelationshipProperties
public class Flight2 {

    @Id @GeneratedValue
    private Long id;

    @Property
    private final Date date;
    @Property
    private final Integer baggage;
    @Property
    private final Integer seatsRemaining;
    @Property
    private final Integer price;

    @TargetNode
    private final Airport airport;

    public Flight2(Date date, Integer baggage, Integer seatsRemaining, Integer price) {
        this.date = date;
        this.baggage = baggage;
        this.seatsRemaining = seatsRemaining;
        this.price = price;
        this.airport = null;
    }

    public Date getDate() {
        return date;
    }

    public Integer getBaggage() {
        return baggage;
    }

    public Integer getSeatsRemaining() {
        return seatsRemaining;
    }

    public Integer getPrice() {
        return price;
    }
}
