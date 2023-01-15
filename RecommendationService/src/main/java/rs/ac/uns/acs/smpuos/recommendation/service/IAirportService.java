package rs.ac.uns.acs.smpuos.recommendation.service;

import rs.ac.uns.acs.smpuos.recommendation.model.Airport;
import java.util.List;


public interface IAirportService {

    List<List<Airport>> findPathsBetweenAirports(Airport src, Airport dst, int skipValue, int limitValue);

}
