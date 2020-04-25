package com.manhpd.array;

/**
 * Use Array to implement stack
 *
 */
public class ArrayStack {

    private final int maxSize = 100;

    // always point to the first position of stack
    private int stackPointer;

    private Element[] data;

    public ArrayStack() {
        this.stackPointer = -1;
        this.data = new Element[this.maxSize];
    }

    public ArrayStack(int size) {
        this.stackPointer = -1;
        this.data = new Element[size];
    }

    public void push(Element e) throws Exception {
        if (this.stackPointer >= this.maxSize) {
            throw new Exception("Stack overflow");
        }

        ++this.stackPointer;
        this.data[this.stackPointer] = e;
    }

    public void pop() throws Exception {
        if (this.stackPointer <= -1) {
            throw new Exception("Stack underflow");
        }

        --this.stackPointer;
    }

    public Element peek() {
        return this.data[this.stackPointer];
    }

}

