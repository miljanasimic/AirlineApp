package rs.ac.uns.acs.smpuos.recommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.smpuos.recommendation.model.Airport;
import rs.ac.uns.acs.smpuos.recommendation.service.IAirportService;
import java.util.List;


@RestController
public class RecommendationController {

    @Autowired
    private IAirportService airportService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "test";
    }
    @GetMapping(value = "/recommend")
    public List<List<Airport>> getPathsFromToAirport(@RequestParam String srcAirport,
                                                 @RequestParam String dstAirport,
                                                 @RequestParam Integer skip,
                                                 @RequestParam Integer limit)
    {
        return airportService.findPathsBetweenAirports(new Airport(srcAirport), new Airport(dstAirport), skip, limit);
    }


}
