package com.manhpd.representation.adjacency_list;

import com.manhpd.representation.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ALGraph {

    private ArrayList<List<Node>> graphs;

    public ALGraph(int capacity) {
        this.graphs = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; ++i) {
            this.graphs.add(new LinkedList<>());
        }
    }

    public List<Node> getNodesWith(int vertex) {
        return this.graphs.get(vertex);
    }

    /**
     * an edge from u vertex to v vertex
     *
     * @param u
     * @param v
     */
    public void addEdge(Node u, Node v) {
        Objects.requireNonNull(u);
        Objects.requireNonNull(v);

        this.graphs.get(u.value).add(v);
        this.graphs.get(v.value).add(u);
    }

    public void display() {
        int len = this.graphs.size();

        for (int i = 1; i < len; ++i) {
            List<Node> nodes = this.graphs.get(i);

            System.out.println("Vertex " + i + " connect with: ");
            for (Node node : nodes) {
                System.out.print(" --> " + node.value + " ");
            }

            System.out.println();
        }
    }

}
