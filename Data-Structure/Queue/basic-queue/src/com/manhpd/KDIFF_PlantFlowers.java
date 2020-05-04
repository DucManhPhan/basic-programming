package com.manhpd;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Refer: https://vn.spoj.com/problems/KDIFF/
 *
 * Constraints:
 * 1 ≤ N ≤ 3 * 105.
 * 30% test cases that have 1 ≤ N ≤ 30.
 * 50% test cases that have 1 ≤ N ≤ 103.
 * nums[i] <= 10^9.
 */
public class KDIFF_PlantFlowers {

    public static void main(String[] args) {
//        int[] nums = {0, 1, 3, 2, 5, 4};
        int[] nums = {0, 1, 3, 5, 2, 4};
        int distance = 2;

        int res = findNumOfFlowerSide(nums, distance);
        System.out.println(res);
    }

    /**
     *
     *
     * @param nums
     * @param distance
     * @return
     */
    public static int findNumOfFlowerSide(int[] nums, int distance) {
        Deque<Integer> dmin = new LinkedList<>();
        Deque<Integer> dmax = new LinkedList<>();
        int[] f = new int[nums.length];
        int ans = 0;

        for (int j = 1, i = 1; j < nums.length; ++j) {
            push(dmin, dmax, nums[j]);
            while (dmax.peekFirst() - dmin.peekFirst() > distance) pop(dmin, dmax, nums[i++]);
            f[j] = j - i + 1;
        }

        for (int i = 1; i < nums.length; ++i) {
            ans = Math.max(ans, f[i] + f[i - f[i]]);
            f[i] = Math.max(f[i - 1], f[i]);
        }

        return ans;
    }

    public static void push(Deque<Integer> dmin, Deque<Integer> dmax, int x) {
        while (dmin.size() > 0 && x < dmin.peekLast()) dmin.pollLast();
        dmin.addLast(x);

        while (dmax.size() > 0 && x > dmax.peekLast()) dmax.pollLast();
        dmax.addLast(x);
    }

    public static void pop(Deque<Integer> dmin, Deque<Integer> dmax, int x) {
        if (x == dmin.peekFirst()) dmin.pollFirst();
        if (x == dmax.peekFirst()) dmax.pollFirst();
    }

}
