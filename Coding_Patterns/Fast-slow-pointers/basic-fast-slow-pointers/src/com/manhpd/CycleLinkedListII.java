package com.manhpd;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 *
 * Ex1: Input: head = [3,2,0,-4], pos = 1
 *      Output: tail connects to node index 1
 *      Explanation: There is a cycle in the linked list, where tail connects to the second node.
 *
 * Ex2: Input: head = [1,2], pos = 0
 *      Output: tail connects to node index 0
 *      Explanation: There is a cycle in the linked list, where tail connects to the first node.
 *
 * Ex3: Input: head = [1], pos = -1
 *      Output: no cycle
 *      Explanation: There is no cycle in the linked list.
 *
 */
public class CycleLinkedListII {

    /**
     * Using slow/fast pointer to determine the length of Linked List cycle.
     *
     * @param head
     * @return
     */
    public static ListNode findCycleStart(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        int lengthCycle = findLengthCycle(head);
        if (lengthCycle == -1) {
            return null;
        }

        // jump fast node with lengthCycle steps
        while (lengthCycle > 0) {
            fast = fast.next;
            --lengthCycle;
        }

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    public static int findLengthCycle(ListNode head) {
        if (head == null) {
            return -1;
        }

        ListNode slow = head;
        ListNode fast = head;
        int lengthCycle = 0;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            ++lengthCycle;

            if (slow == fast) {
                return lengthCycle;
            }
        }

        return -1;
    }

    /**
     * Using HashSet to get the first duplicated node. It is exactly the cycle start node
     *
     * @param head
     * @return
     */
    public static ListNode findCycleStartII(ListNode head) {
        ListNode tmp = head;
        Set<ListNode> uniqueNodes = new HashSet<>();

        while (tmp != null) {
            if (uniqueNodes.contains(tmp)) {
                return tmp;
            } else {
                uniqueNodes.add(tmp);
            }

            tmp = tmp.next;
        }

        return null;
    }

    /**
     * Using Floyd's tortoise and hare algorithm
     *
     * @param args
     */
    public static ListNode getFirstMeetNode(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode tortoise = head;
        ListNode hare = head;

        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;

            if (tortoise == hare) {
                return tortoise;
            }
        }

        return null;
    }

    public static ListNode findCycleStartIII(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode firstMeetNode = getFirstMeetNode(head);
        if (firstMeetNode == null) {
            return null;
        }

        ListNode tmpNode = head;
        while (tmpNode != firstMeetNode) {
            tmpNode = tmpNode.next;
            firstMeetNode = firstMeetNode.next;
        }

        return tmpNode;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList cycle start: " + CycleLinkedListII.findCycleStartIII(head).value);

        head.next.next.next.next.next.next = head.next.next.next;
//        int length = CycleLinkedListII.findLengthCycle(head);
//        System.out.println("Length of cycle: " + length);
        System.out.println("LinkedList cycle start: " + CycleLinkedListII.findCycleStartIII(head).value);

        head.next.next.next.next.next.next = head;
//        length = CycleLinkedListII.findLengthCycle(head);
//        System.out.println("Length of cycle: " + length);
        System.out.println("LinkedList cycle start: " + CycleLinkedListII.findCycleStartIII(head).value);
    }

}