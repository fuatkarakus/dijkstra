package com.koucs.search;

import com.koucs.domain.City;
import com.koucs.domain.Graph;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.koucs.util.Utils.getCitiesNeighbourhood;

public class GraphBuilder {

    private GraphBuilder() {}

    public static Graph build() throws IOException {

        Graph graph = new Graph(82);

        Map<City, List<City>> neighbour = getCitiesNeighbourhood();

        neighbour.entrySet()
                .forEach(
                        e -> e.getValue()
                                .forEach( v -> graph.addEdge(Integer.parseInt(e.getKey().getLicensePlate()),
                                                Integer.parseInt(v.getLicensePlate()),
                                                e.getKey().distanceToCity(v))
                                )
                );

        return graph;

    }

}
