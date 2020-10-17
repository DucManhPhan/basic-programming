package com.manhpd.representation.adjacency_matrix;

/**
 * The undirected graph described:
 *
 * 1 ---------------- 4
 *                  / |
 *                 /  |
 *                2   |
 *                 \  |
 *                  \ |
 * 3 --------------- 5
 */
public class AdjencyMatrixCase {

    public static void main(String[] args) {
        int capacity = 6;
        initAMGraph(capacity);
    }

    public static void initAMGraph(int capacity) {
        AMGraph graph = new AMGraph(capacity);

        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(5, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 5);

        graph.display();
    }

}
