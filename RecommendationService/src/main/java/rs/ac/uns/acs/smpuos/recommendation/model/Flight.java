package rs.ac.uns.acs.smpuos.recommendation.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

import java.util.Date;

public class Flight {

    @Id @GeneratedValue
    private Long id;

    private final Date date;

    private final Integer baggage;

    private final Integer seatsRemaining;

    private final Integer price;


    private final String srcCode;

    private final String dstCode;

    public Flight(Date date, Integer baggage, Integer seatsRemaining, Integer price, String srcCode, String dstCode) {
        this.date = date;
        this.baggage = baggage;
        this.seatsRemaining = seatsRemaining;
        this.price = price;
        this.srcCode = srcCode;
        this.dstCode = dstCode;
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

    public String getSrcCode() {
        return srcCode;
    }

    public String getDstCode() {
        return dstCode;
    }
}
