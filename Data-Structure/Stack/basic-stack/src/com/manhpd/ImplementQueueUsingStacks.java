package com.manhpd;

import java.util.Stack;

/**
 * Implement Queue using stacks.
 * What's the time complexity of various queue operations for this implementation?
 *
 */
public class ImplementQueueUsingStacks {

    private Stack<Integer> addStack;

    private Stack<Integer> removeStack;

    /**
     * enqueue() and dequeue() method with dequeue operation costly
     *
     * This way is more efficient than the way that implement enqueueI() and dequeueI()
     *
     * @param value
     */
    public void enqueue(int value) {
        this.addStack.push(value);
    }

    public int dequeue() throws Exception {
        if (this.removeStack.isEmpty()) {
            if (this.addStack.isEmpty()) {
                throw new Exception("Queue is empty");
            }

            while (!this.addStack.isEmpty()) {
                int value = this.addStack.pop();
                this.removeStack.push(value);
            }
        }

        return this.removeStack.pop();
    }

    /**
     * enqueueI() and dequeueI() method with enqueue operation costly
     *
     */
    public void enqueueI(int value) {
        while (!this.removeStack.isEmpty()) {
            this.addStack.push(this.removeStack.pop());
        }

        this.removeStack.push(value);

        while (!this.addStack.isEmpty()) {
            this.removeStack.push(this.addStack.pop());
        }
    }

    public int dequeueI() throws Exception {
        if (this.removeStack.isEmpty()) {
            throw new Exception("Queue is empty");
        }

        return this.removeStack.pop();
    }

}
