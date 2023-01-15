package rs.ac.uns.acs.smpuos.recommendation.service;

import org.neo4j.driver.Value;
import org.neo4j.driver.internal.value.ListValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.smpuos.recommendation.model.Airport;
import rs.ac.uns.acs.smpuos.recommendation.repository.AirportRepository;
import java.util.ArrayList;
import java.util.List;


@Service
public class AirportService implements IAirportService{

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public List<List<Airport>> findPathsBetweenAirports(Airport src, Airport dst, int skipValue, int limitValue) {
        List<ListValue> response =airportRepository.findPathsBetweenAirports(src.getCode(), dst.getCode(), skipValue, limitValue);
        List<List<Airport>> allPaths = new ArrayList<>();
        for (ListValue el: response) {
            List<Airport> path = new ArrayList<>();
            for (Value airport: el.values())
                path.add(new Airport(airport.asString()));

            allPaths.add(path);
        }
        return allPaths;
    }


}
