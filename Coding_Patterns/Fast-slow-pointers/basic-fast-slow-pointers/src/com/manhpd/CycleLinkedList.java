package com.manhpd;


import java.util.HashSet;
import java.util.Set;

/**
 * Given the head of a Singly LinkedList, write a function to determine if the LinkedList has a cycle in it or not.
 *
 */
public class CycleLinkedList {

    /**
     * Using two pointer technique
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        ListNode slowPointer = head;
        ListNode fastPointer = head;

        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;

            if (slowPointer == fastPointer) {
                return true;
            }
        }

        return false;
    }

    /**
     * Because based on the duplicated element when we scan elements, so we can use HashSet to deal with this problem.
     *
     * @param head
     * @return
     */
    public static boolean hasCycleII(ListNode head) {
        Set<ListNode> markNodes = new HashSet<>();
        ListNode tmp = head;

        while (tmp != null && tmp.next != null) {
            if (markNodes.contains(tmp)) {
                return true;
            } else {
                markNodes.add(tmp);
            }

            tmp = tmp.next;
        }

        return false;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        System.out.println("LinkedList has cycle: " + CycleLinkedList.hasCycleII(head));

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList has cycle: " + CycleLinkedList.hasCycleII(head));

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList has cycle: " + CycleLinkedList.hasCycleII(head));
    }

}

class ListNode {
    int value = 0;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}
