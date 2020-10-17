package com.manhpd.representation.adjacency_matrix;

public class AMGraph {

    private int[][] graph;

    private int capacity;

    public AMGraph(int capacity) {
        this.capacity = capacity;
        this.graph = new int[capacity][capacity];
    }

    public int getCapacity() {
        return this.capacity;
    }

    public boolean isAnEdge(int u, int v) {
        return this.graph[u][v] == 1;
    }

    public void addEdge(int u, int v) {
        this.graph[u][v] = 1;
        this.graph[v][u] = 1;
    }

    public void display() {
        for (int i = 1; i < this.capacity; ++i) {
            System.out.println("Vertex " + i + " connect with: ");

            for (int j = 1; j < this.capacity; ++j) {
                if (this.graph[i][j] == 1) {
                    System.out.print(" --> " + j + " ");
                }
            }

            System.out.println();
        }
    }

}
