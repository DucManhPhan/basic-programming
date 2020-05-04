package com.manhpd;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DequeMinMax {

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 4, 2, 8};
        int j = 2;
        int i = 4;
        int[] minMax = new int[2];

        dequeueMinMax(nums, i, j , minMax);
        IntStream.of(minMax).forEach(System.out::println);
    }

    public static void dequeueMinMax(int[] nums, int i, int j, int[] minMax) {
        Deque<Integer> dmin = new LinkedList<>();
        Deque<Integer> dmax = new LinkedList<>();

        for (int cnt = 0; cnt <= i; ++cnt) {
            while (dmin.size() > 0 && nums[dmin.peekLast()] > nums[cnt]) dmin.pollLast();
            while (dmax.size() > 0 && nums[dmax.peekLast()] < nums[cnt]) dmax.pollLast();

            dmin.addLast(cnt);
            dmax.addLast(cnt);
        }

        for (int cnt = 0; cnt < j; ++cnt) {
            if (dmin.peekFirst() == cnt) dmin.pollFirst();
            if (dmax.peekFirst() == cnt) dmax.pollFirst();
        }

        minMax[0] = dmin.peekFirst();      // min
        minMax[1] = dmax.peekFirst();      // max
    }

}
