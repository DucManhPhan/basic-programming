package com.manhpd.graph_representation;

public class ApplicationGraph {

    public static void main(String[] args) {
        adjacencyListGraph();
    }

    public static void adjacencyListGraph() {
        int nVertices = 5;
        AdjacencyListUnDirectedGraph graph = new AdjacencyListUnDirectedGraph(nVertices);
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);

        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        graph.printGraph();
    }

}
