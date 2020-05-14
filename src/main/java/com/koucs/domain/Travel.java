package com.koucs.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Travel implements Comparable<Travel>{

    private List<City> path = new LinkedList<>();

    public void setPath(List<City> path) {
        this.path.addAll(path);
    }

    public List<City> getPath() {
        return path;
    }

    public Travel(List<City> path) {
        this.path = path;
    }

    public Travel() {
    }

    public City getCity(int index) {
        return path.get(index);
    }

    public double getDistance() {
        double distance = 0;
        for (int index = 0; index < path.size(); index++) {
            City starting = getCity(index);
            City destination;
            if (index + 1 < path.size()) {
                destination = getCity(index + 1);
            } else {
                destination = getCity(0);
            }
            distance += starting.distanceToCity(destination);
        }
        return distance;
    }

    @Override
    public String toString() {
        return "travel: " + path.stream().map(City::getName).collect(Collectors.toList()) + "distance: " + getDistance();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Travel travel1 = (Travel) o;
        return path.equals(travel1.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }

    @Override
    public int compareTo(Travel o) {
        return  Double.compare(getDistance(), o.getDistance());
    }
}
