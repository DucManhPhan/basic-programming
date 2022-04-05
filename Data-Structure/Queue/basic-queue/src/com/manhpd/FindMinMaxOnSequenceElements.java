package com.manhpd;

import java.util.Deque;
import java.util.LinkedList;

public class FindMinMaxOnSequenceElemens {

    public static void main(String[] args) {
//        int[] nums = {1, 3, 5, 4, 2, 8};
        int[] nums = {3, 4, 3, 1};
//        usingBruteForce(nums);
        usingQueueSolution(nums);
    }

    public static void usingBruteForce(int[] nums) {
        int[] l = new int[nums.length];
        int[] r = new int[nums.length];

        for (int i = 0; i < nums.length; ++i) {
            // calculate l
            l[i] = i;
            while (l[i] > -1 && nums[i] <= nums[l[i]]) --l[i];
            ++l[i];
//            System.out.println("At index = " + i + ", l = ");
//            for (int j = 0; j <= i; ++j) {
//                System.out.print(l[j] + ", ");
//            }
//
//            System.out.println();

            // Calculate r
            r[i] = i;
            while (r[i] < nums.length && nums[i] <= nums[r[i]]) ++r[i];
            --r[i];
//            System.out.println("At index = " + i + ", r = ");
//            for (int j = 0; j <= i; ++j) {
//                System.out.print(r[j] + ", ");
//            }
//
//            System.out.println();
        }
    }

    public static void usingQueueSolution(int[] nums) {
//        Deque<Integer> dmin = new LinkedList<>();
//        Deque<Integer> dmax = new LinkedList<>();
        Deque<Integer> deque = new LinkedList<>();

        int[] l = new int[nums.length];
        int[] r = new int[nums.length];

        // Calculate l
        for (int i = 0; i < nums.length; ++i) {
            while (deque.size() > 0 && nums[deque.peekLast()] >= nums[i]) deque.pollLast();
            l[i] = deque.isEmpty() ? 0 : deque.peekLast() + 1;
            deque.addLast(i);

            // // print dmin
//            System.out.println("At index = " + i + ", l = ");
//            for (Integer item : dmin) {
//                System.out.print(item + ", ");
////                System.out.println();
//            }
//
//            System.out.println();

            // print l
//            System.out.println("At index = " + i + ", l = ");
//            for (int j = 0; j <= i; ++j) {
//                System.out.print(l[j] + ", ");
//            }
//
//            System.out.println();
        }

//        System.out.println();

        // Calculate r
        deque.clear();
        for (int i = nums.length - 1; i >= 0; --i) {
            while (deque.size() > 0 && nums[deque.peekLast()] >= nums[i]) deque.pollLast();
            r[i] = deque.isEmpty() ? nums.length - 1 : deque.peekLast() - 1;
            deque.addLast(i);

            // print dmax
//            System.out.println("At index = " + i + ", r = ");
//            for (Integer item : dmax) {
//                System.out.print(item + ", ");
//            }
//
//            System.out.println();

            // print r
//            System.out.println("At index = " + i + ", r = ");
//            for (int j = nums.length - 1; j >= i; --j) {
//                System.out.print(r[j] + ", ");
//            }
//
//            System.out.println();
        }

        int res = nums[0] * (r[0] - l[0] + 1);
        for (int i = 1; i < nums.length; ++i) {
            int tmp = nums[i] * (r[i] - l[i] + 1);
            res = Math.max(res, tmp);
        }

        System.out.println("Result: " + res);
    }

}
