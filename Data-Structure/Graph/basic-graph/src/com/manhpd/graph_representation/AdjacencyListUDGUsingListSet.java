package com.manhpd.graph_representation;

import java.util.*;

/**
 *
 */
public class AdjacencyListUDGUsingListSet {

    private List<Set<Integer>> adjacencyList;

    public AdjacencyListUDGUsingListSet(int nVertices) {
        this.adjacencyList = new ArrayList<>(nVertices);
        for (int i = 0; i < nVertices; ++i) {
            this.adjacencyList.add(new TreeSet<>());    // use TreeSet to contains elements
//            this.adjacencyList.add(new HashSet<>());    // use HashSet to contains elements
        }
    }

    public void addEdge(int u, int v) {
        this.adjacencyList.get(u).add(v);
        this.adjacencyList.get(v).add(u);
    }

}
