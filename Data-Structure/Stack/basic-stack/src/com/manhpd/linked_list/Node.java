package com.manhpd.linked_list;

/**
 * Node of the Stack that implement by using Linked List
 */
public class Node {

    public int value;

    public Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }

    public int getValue() {
        return this.value;
    }

}
