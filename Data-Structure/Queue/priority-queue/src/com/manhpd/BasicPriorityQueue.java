package com.manhpd;

import java.util.Collections;
import java.util.PriorityQueue;

public class BasicPriorityQueue {
    public static void main(String[] args) {
        testIntegerPriorityQueue();
    }

    private static void testIntegerPriorityQueue() {
        // natural order
//        PriorityQueue<Integer> intQueue = new PriorityQueue<>();
//
//        intQueue.add(9);
//        intQueue.add(2);
//        intQueue.add(4);
//
//        while (!intQueue.isEmpty()) {
//            System.out.println(intQueue.poll()); // 2 - 4 - 9
//        }

        // inverse order
//        PriorityQueue<Integer> reversedIntQueue = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> reversedIntQueue = new PriorityQueue<>(Collections.reverseOrder());

        reversedIntQueue.add(2);
        reversedIntQueue.add(4);
        reversedIntQueue.add(9);

        while (!reversedIntQueue.isEmpty()) {
            System.out.println(reversedIntQueue.poll()); // 9 - 4 - 2
        }
    }
}
