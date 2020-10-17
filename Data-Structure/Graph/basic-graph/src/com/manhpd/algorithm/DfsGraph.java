package com.manhpd.algorithm;

import com.manhpd.representation.Node;
import com.manhpd.representation.adjacency_list.ALGraph;
import com.manhpd.representation.adjacency_matrix.AMGraph;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

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
//        AMGraph graph = initAMGraph(capacity);
        boolean[] visited = new boolean[capacity];

//        dfs(graph, visited, new Node(1));
//        dfs(graph, visited, 1);
        dfsUnRecursive(graph, visited);
    }

    /**
     * Using recursive version
     *
     * Result: 1 --> 2 --> 4 --> 5 --> 3 --> 6
     *
     * @param graph
     * @param visited
     * @param node
     */
    public static void dfs(ALGraph graph, boolean[] visited, Node node) {
        Objects.requireNonNull(node);

        if (visited[node.value]) {
            return;
        }

        visited[node.value] = true;
        System.out.print("" + node.value + " --> ");

        List<Node> nodes = graph.getNodesWith(node.value);
        for (Node tmpNode : nodes) {
            if (!visited[tmpNode.value]) {
                dfs(graph, visited, tmpNode);
            }
        }
    }

    public static void dfs(AMGraph graph, boolean[] visited, int node) {
        System.out.print("" + node + " --> ");
        visited[node] = true;

        for (int i = 1; i < graph.getCapacity(); ++i) {
            if (graph.isAnEdge(node, i) && !visited[i]) {
                dfs(graph, visited, i);
            }
        }
    }

    /**
     * Using stack data structure
     *
     * @param graph
     * @param visited
     */
    public static void dfsUnRecursive(ALGraph graph, boolean[] visited) {
        Stack<Node> stkNodes = new Stack<>();
        stkNodes.push(new Node(1));

        while (!stkNodes.isEmpty()) {
            Node topNode = stkNodes.pop();

            // prepare for the node iterate in twice times
            if (!visited[topNode.value]) {
                visited[topNode.value] = true;
                System.out.print("" + topNode.value + " --> ");
            }

            List<Node> childNodes = graph.getNodesWith(topNode.value);
            for (Node tmpNode : childNodes) {
                if (!visited[tmpNode.value]) {
                    stkNodes.push(tmpNode);
                }
            }
        }
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

        return graph;
    }

    public static AMGraph initAMGraph(int capacity) {
        AMGraph graph = new AMGraph(capacity);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 6);

        return graph;
    }

}
