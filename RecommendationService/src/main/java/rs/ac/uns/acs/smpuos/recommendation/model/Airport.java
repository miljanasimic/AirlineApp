package rs.ac.uns.acs.smpuos.recommendation.model;


import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.*;

@Node
public class Airport {
    @Id
    private final String code;
    public Airport(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

}
