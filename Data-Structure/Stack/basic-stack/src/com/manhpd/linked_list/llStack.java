package com.manhpd.linked_list;

import java.util.Objects;

/**
 * Implement stack by using linked list
 *
 */
public class llStack {

    private Node stackPointer;

    public void push(Node node) {
        if (Objects.isNull(node)) {
            System.out.println("Data can not be null");
            return;
        }

        node.next = this.stackPointer;
        this.stackPointer = node;
    }

    public void pop() {
        if (Objects.isNull(this.stackPointer)) {
            System.out.println("Stack has no data");
            return;
        }

        this.stackPointer = this.stackPointer.next;
    }

    public Node peek() {
        return this.stackPointer;
    }

    public boolean isEmpty() {
        return Objects.isNull(this.stackPointer) ? true : false;
    }

}
