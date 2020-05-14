package com.koucs.search;

import com.koucs.domain.*;
import com.koucs.util.Pair;
import com.koucs.util.Utils;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static com.koucs.util.Utils.getRoutePairs;
import static com.koucs.util.Utils.listPermutations;

public class Search {

    public void shortestPaths() throws IOException {
        Instant start = java.time.Instant.now();

        Map<Integer, Graph> graphMap = new HashMap<>();

        // Get User Input Route without Kocaeli
        List<String> userRoute = Utils.getInstance().getRoute();

        // copy for permutation
        List<String> userRouteCopy = new ArrayList<>(userRoute);

        // calculate permutations of given route
        // now we had all route between given cities
        List<List<String>> permutation = listPermutations(userRouteCopy);

        // add Kocaeli(41) to each route start and end
        permutation.forEach(i -> {
            i.add(0, "41");
            i.add("41");
        });

        if(userRoute.stream().noneMatch(i-> i.equals("41"))) {
            Graph g = GraphBuilder.build();
            Dijkstra obj = new Dijkstra();
            obj.calculate(g.getVertex(41));
            graphMap.put(41, g);
        }

        for (String city : userRoute) {
            Graph graph = GraphBuilder.build();
            int ci = Integer.parseInt(city);
            Vertex v = graph.getVertex(ci);
            new Dijkstra().calculate(v);
            graphMap.put(Integer.parseInt(city), graph);
        }

        List<Travel> travels = new ArrayList<>();
        for (List<String> route: permutation) {
             travels.add(findTravel(graphMap, getRoutePairs(route))) ;
        }

        travels.sort(Travel::compareTo);

        Set<Travel> travelSet = new TreeSet<>(travels);

        System.out.println("En kisa 5 rota ");

        travelSet.stream().limit(5).forEach(System.out::println);

        Instant end = java.time.Instant.now();
        Duration between = Duration.between(start, end);

        System.out.format("%02d:%02d.%04d %n", between.toMinutes(), between.getSeconds(), between.toMillis()); // 00:01.1001
    }

    public Travel findTravel(Map<Integer, Graph> graphMap, List<Pair<Integer, Integer>> pairs) {

        Travel travel = new Travel();

        Map<String, City> cities = Utils.getInstance().getCities();

        for (Pair<Integer, Integer> pair: pairs) {

            Graph graph = graphMap.get(pair.getFirst());

            Vertex v = graph.getVertex(pair.getSecond());

            travel.setPath(v.path
                    .stream()
                    .map( i -> cities.get(i.name))
                    .collect(Collectors
                            .toCollection(LinkedList::new)));

        }

        return travel;
    }

}
