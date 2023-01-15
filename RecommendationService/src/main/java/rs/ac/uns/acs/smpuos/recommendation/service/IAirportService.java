package rs.ac.uns.acs.smpuos.recommendation.service;

import org.neo4j.driver.internal.value.ListValue;
import rs.ac.uns.acs.smpuos.recommendation.model.Airport;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IAirportService {

    List<List<Airport>> findPathsBetweenAirports(Airport src, Airport dst, int skipValue, int limitValue);

}
