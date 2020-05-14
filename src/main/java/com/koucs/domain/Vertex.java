package com.koucs.domain;

import java.util.*;

public class Vertex implements Comparable<Vertex>{

    public final String name;

    public ArrayList<Edge> neighbours;

    public LinkedList<Vertex> path;

    public LinkedList<Vertex> getPath() {
        return path;
    }

    public void setPath(Vertex path) {
        this.path.add(path);
    }

    public double minDistance = Double.POSITIVE_INFINITY;

    public Vertex previous;

    public int compareTo(Vertex other){
        return Double.compare(minDistance,other.minDistance);
    }

    public Vertex(String name){
        this.name = name;
        neighbours = new ArrayList<>();
        path = new LinkedList<>();
    }

    public String toString(){
        return name;
    }
}
