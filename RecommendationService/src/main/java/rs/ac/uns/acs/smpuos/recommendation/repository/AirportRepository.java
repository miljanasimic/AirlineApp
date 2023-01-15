package rs.ac.uns.acs.smpuos.recommendation.repository;

import org.neo4j.driver.internal.value.ListValue;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;
import rs.ac.uns.acs.smpuos.recommendation.model.Airport;
import rs.ac.uns.acs.smpuos.recommendation.service.AirportService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface AirportRepository extends Neo4jRepository<Airport, String> {

    @Query("MATCH p=(src:Airport)-[FLY_TO*1..4]->(dst:Airport)\n" +
            "  WHERE src.code=$src AND dst.code=$dst\n" +
            "  WITH [x in nodes(p) | x.code] as list_path\n" +
            "  WHERE apoc.coll.containsDuplicates(list_path)=FALSE\n" +
            "  WITH size(list_path) as airport_num, list_path\n" +
            "  ORDER BY airport_num\n" +
            "  SKIP $skipValue\n" +
            "  LIMIT $limitValue\n" +
            "  RETURN list_path")
    List<ListValue> findPathsBetweenAirports(String src, String dst, int skipValue, int limitValue);
}
