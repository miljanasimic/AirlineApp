package rs.ac.uns.acs.smpuos.recommendation.controller;

import com.fasterxml.jackson.annotation.JsonValue;
import org.neo4j.driver.internal.value.ListValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rs.ac.uns.acs.smpuos.recommendation.model.Airport;
import rs.ac.uns.acs.smpuos.recommendation.repository.AirportRepository;
import rs.ac.uns.acs.smpuos.recommendation.service.IAirportService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public class RecommendationController {

    @Autowired
    private IAirportService airportService;

    /*public RecommendationController(AirportRepository repository) {
        this.airportRepository = repository;
    }*/
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "cao";
    }
//    @GetMapping(value = "/airports")
//    public List<Airport> getAirports(String code){
//        //List a= airportRepository.findAll();
//        //System.out.println(a);
//        //return a;
//        return airportService.findAll();
//    }


    @GetMapping(value = "/recommend")
    public List<List<Airport>> getPathsFromToAirport(@RequestParam String srcAirport,
                                                 @RequestParam String dstAirport,
                                                 @RequestParam Integer skip,
                                                 @RequestParam Integer limit)
    {
        return airportService.findPathsBetweenAirports(new Airport(srcAirport), new Airport(dstAirport), skip, limit);
    }

    /*@RequestMapping(value = "/airport", method = RequestMethod.GET)
    @ResponseBody
    public Airport getAirport(@RequestParam String code){
        Airport a = airportRepository.findOneByCode(code);
        System.out.println(a);
        return a.;
    }*/
}
