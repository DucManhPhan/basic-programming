package com.manhpd.graph_representation;

import java.util.*;

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

    public void printGraph() {
        for (int i = 0; i < this.adjacencyList.size(); ++i) {
            Object[] nodes = this.adjacencyList.get(i).toArray();

            System.out.println("\nAdjacency list of vertex: " + i);
            System.out.print("head");

            for (int j = 0; j < nodes.length; ++j) {
                System.out.print(" --> " + (int)nodes[j]);
            }

            System.out.println();
        }
    }

}
