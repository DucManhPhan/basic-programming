package com.manhpd;

import java.util.Stack;

/**
 * Given the head of a Singly LinkedList, write a method to check if the LinkedList is a palindrome or not.
 * Your algorithm should use constant space and the input LinkedList should be in the original form once the algorithm is finished.
 * The algorithm should have O(N) time complexity where ‘N’ is the number of nodes in the LinkedList.
 *
 * Example 1:
 * Input: 2 -> 4 -> 6 -> 4 -> 2 -> null
 * Output: true
 *
 * Example 2:
 * Input: 2 -> 4 -> 6 -> 4 -> 2 -> 2 -> null
 * Output: false
 *
 */
public class PalindromicLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(2);
        System.out.println("Is palindrome: " + PalindromicLinkedList.isPalindrome(head));

        head.next.next.next.next.next = new ListNode(2);
        System.out.println("Is palindrome: " + PalindromicLinkedList.isPalindrome(head));
    }

    /**
     * 1st way: Split linked list into two parts:
     * 1. from start to middle position.
     * 2. from middle to end position.
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        // find the mid element
        ListNode middle = findMiddle(head);
        if (middle == null) {
            return false;
        }

        // get the values from the end to start
        Stack<Integer> stk = new Stack<>();
        ListNode tmp = middle.next;
        while (tmp != null) {
            stk.push(tmp.value);
            tmp = tmp.next;
        }

        // compare with the beginning nodes
        while (head != middle) {
            int currentValue = stk.pop();
            if (head.value != currentValue) {
                return false;
            }

            head = head.next;
        }

        if (!stk.isEmpty()) {
            return false;
        }

        return true;
    }

    private static ListNode findMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     * 2nd way:
     *
     */
    public static boolean isPalindromeV2(ListNode head) {
        return false;
    }

}
