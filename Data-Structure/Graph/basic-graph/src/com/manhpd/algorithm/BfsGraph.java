package com.manhpd.algorithm;

import com.manhpd.representation.Node;
import com.manhpd.representation.adjacency_list.ALGraph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *          1
 *        /   \
 *       2     3
 *       |  \  |
 *       4 -- 5
 *        \  /
 *         6
 */
public class BfsGraph {

    public static void main(String[] args) {
        int capacity = 7;
        ALGraph graph = initALGraph(capacity);

        bfs(graph, new Node(1), capacity);
    }

    public static void bfs(ALGraph graph, Node beginNode, int capacity) {
        boolean[] visited = new boolean[capacity];
        Queue<Node> queue = new LinkedList<>();
        queue.add(beginNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if (visited[currentNode.value]) {
                continue;
            }

            visited[currentNode.value] = true;
            List<Node> nodes = graph.getNodesWith(currentNode.value);

            for (Node tmpNode : nodes) {
                queue.add(tmpNode);
            }

            System.out.print("" + currentNode.value + " --> ");
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

//        graph.display();

        return graph;
    }

}
