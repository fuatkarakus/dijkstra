package com.koucs.domain;

import java.util.*;

public class Graph {

    private ArrayList<Vertex> vertices; // nodes

    public Graph(int numberVertices){
        vertices = new ArrayList<>();
        for(int i=0;i<numberVertices;i++){
            vertices.add(new Vertex(String.valueOf(i)));
        }
    }

    public void addEdge(int src, int dest, double weight){
        Vertex s = vertices.get(src);
        Edge newEdge = new Edge(vertices.get(dest), weight);
        s.neighbours.add(newEdge);
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getVertex(int vert){
        return vertices.get(vert);
    }
}
