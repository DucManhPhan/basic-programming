package com.manhpd;

import java.util.*;

/**
 * Given a list of intervals, merge all the overlapping intervals to produce a list that has only mutually exclusive intervals.
 *
 * Example 1:
 * Intervals: [[1,4], [2,5], [7,9]]
 * Output: [[1,5], [7,9]]
 * Explanation: Since the first two intervals [1,4] and [2,5] overlap, we merged them into one [1,5].
 *
 * Example 2:
 * Intervals: [[6,7], [2,4], [5,9]]
 * Output: [[2,4], [5,9]]
 * Explanation: Since the intervals [6,7] and [5,9] overlap, we merged them into one [5,9].
 *
 * Example 3:
 * Intervals: [[1,4], [2,6], [3,5]]
 * Output: [[1,6]]
 * Explanation: Since all the given intervals overlap, we merged them into one.
 *
 */
public class MergeIntervals {
    public static void main(String[] args) {
        List<Interval> input = new ArrayList<Interval>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 5));
        input.add(new Interval(7, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(6, 7));
        input.add(new Interval(2, 4));
        input.add(new Interval(5, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 6));
        input.add(new Interval(3, 5));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(2, 6));
        input.add(new Interval(8, 10));
        input.add(new Interval(15, 18));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();
    }

    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> mergedIntervals = new LinkedList<Interval>();
        Comparator<Interval> byStartPoint = Comparator.comparingInt(i -> i.start);

        Collections.sort(intervals, byStartPoint);
        for (int i = 1; i < intervals.size(); ++i) {
            Interval i1, i2;
            if (mergedIntervals.isEmpty()) {
                i1 = intervals.get(i - 1);
                i2 = intervals.get(i);
            } else {
                i1 = mergedIntervals.remove(mergedIntervals.size() - 1);
                i2 = intervals.get(i);
            }

            Interval newInterval = merge(i1, i2);
            if (newInterval == null) {  // non-overlapping
                mergedIntervals.add(i1);
                mergedIntervals.add(i2);
            } else {
                mergedIntervals.add(newInterval);
            }
        }

        return mergedIntervals;
    }

    /**
     * After sorting, i1.start will be always less than or equal to i2.start
     *
     * @param i1
     * @param i2
     */
    private static Interval merge(Interval i1, Interval i2) {
        Interval newInterval = new Interval(-1, -1);

        // overlapping: i1s --- i2s --- i1e
        if (i2.start <= i1.end) {
            if (i1.end <= i2.end) { // i1s --- i2s --- i1e --- i2e
                newInterval.start = i1.start;
                newInterval.end = i2.end;
            } else {
                newInterval = i1;
            }

            return newInterval;
        } else { // non-overlap: i1s --- i1e --- i2s --- i2e
            return null;
        }
    }

    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
