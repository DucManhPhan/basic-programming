package com.manhpd.representation.adjacency_list;

import java.util.ArrayList;
import java.util.List;

/**
 * An array of lists is used. Size of the array is equal to the number of vertices. Let the array be array[].
 * An entry array[i] represents the list of vertices adjacent to the ith vertex.
 * This representation can also be used to represent a weighted graph. The weights of edges can be represented as lists of pairs.
 *
 */
public class AdjacencyListUnDirectedGraph {

    private List<List<Integer>> adjacencyList;

    public AdjacencyListUnDirectedGraph() {
        this.adjacencyList = new ArrayList<>();
    }

    public AdjacencyListUnDirectedGraph(int nVertices) {
        this.adjacencyList = new ArrayList<>(nVertices);
        for (int i = 0; i < nVertices; ++i) {
            this.adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        this.adjacencyList.get(u).add(v);
        this.adjacencyList.get(v).add(u);
    }

    public void printGraph() {
        for (int i = 0; i < this.adjacencyList.size(); ++i) {
            System.out.println("\nAdjacency list of vertex: " + i);
            System.out.print("head");
            for (int j = 0; j < this.adjacencyList.get(i).size(); ++j) {
                System.out.print(" --> " + this.adjacencyList.get(i).get(j));
            }

            System.out.println();
        }
    }

}
