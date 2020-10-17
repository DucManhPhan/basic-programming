package com.manhpd.algorithm;

import com.manhpd.representation.Node;
import com.manhpd.representation.adjacency_list.ALGraph;

/**
 *          1
 *        /   \
 *       2     3
 *       |  \  |
 *       4 -- 5
 *        \  /
 *         6
 */
public class DfsGraph {

    public static void main(String[] args) {
        int capacity = 7;
        ALGraph graph = initALGraph(capacity);
        boolean[] visited = new boolean[capacity];

        dfs(graph, visited, new Node(1));
    }

    /**
     * Using recursive version
     *
     * @param graph
     * @param visited
     * @param node
     */
    public static void dfs(ALGraph graph, boolean[] visited, Node node) {
        if (visited[node.value]) {
            return;
        }

        visited[node.value] = true;

    }

    /**
     * Using stack data structure
     *
     * @param graph
     * @param visited
     */
    public static void dfsUnRecursive(ALGraph graph, boolean[] visited) {

    }

    public static ALGraph initALGraph(int capacity) {
        ALGraph graph = new ALGraph(capacity);

        graph.addEdge(new Node(1), new Node(2));
        graph.addEdge(new Node(1), new Node(3));
        graph.addEdge(new Node(2), new Node(4));
        graph.addEdge(new Node(2), new Node(5));
        graph.addEdge(new Node(3), new Node(5));
        graph.addEdge(new Node(4), new Node(5));
        graph.addEdge(new Node(4), new Node(6));
        graph.addEdge(new Node(5), new Node(6));

//        graph.display();

        return graph;
    }

}
