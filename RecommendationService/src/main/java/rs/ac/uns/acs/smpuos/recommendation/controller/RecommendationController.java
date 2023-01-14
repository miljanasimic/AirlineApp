package rs.ac.uns.acs.smpuos.recommendation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommendationController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "cao";
    }
}
