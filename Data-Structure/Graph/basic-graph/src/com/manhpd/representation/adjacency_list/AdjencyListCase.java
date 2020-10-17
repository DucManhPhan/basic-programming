package com.manhpd.representation.adjacency_list;

import com.manhpd.representation.Node;

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
public class AdjencyListCase {

    public static void main(String[] args) {
        int capacity = 6;
        initALGraph(capacity);
    }

    public static void initALGraph(int capacity) {
        if (capacity < 0) {
            throw new UnsupportedOperationException("The number of vertices are always positive");
        }

        ALGraph graph = new ALGraph(capacity);

        graph.addEdge(new Node(1), new Node(4));
        graph.addEdge(new Node(2), new Node(4));
        graph.addEdge(new Node(5), new Node(4));
        graph.addEdge(new Node(2), new Node(5));
        graph.addEdge(new Node(3), new Node(5));

        graph.display();
    }

}
