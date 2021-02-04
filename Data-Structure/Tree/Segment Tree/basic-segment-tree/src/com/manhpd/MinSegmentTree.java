package com.manhpd;

public class MinSegmentTree {

    private final int[] segTree;

    private final int[] arr;

    private final int[] lazyArr;

    public MinSegmentTree(final int[] arr) {
        int size = this.getMaxSize(arr.length);

        this.arr = arr;
        this.segTree = new int[size];
        this.lazyArr = new int[size];
        this.construct();
    }

    public void construct() {
        this.construct(0, 0, this.arr.length - 1);
    }

    /**
     * Update the elements in the original array with value
     * @param idxOrigin
     * @param value
     */
    public void updateAt(int idxOrigin, int value) {
        if (idxOrigin < 0 || idxOrigin >= this.arr.length) {
            return;
        }

        int diff = value - arr[idxOrigin];
        arr[idxOrigin] = value;

        this.updateAt(0, 0, this.arr.length - 1, idxOrigin, diff);
    }

    /**
     * To update an interval we will keep 3 things in mind.
     * 1. If current segment tree node has any pending update, then first add that pending update to current node.
     * 2. If the interval represented by current node lies completely in the interval to update,
     *      then update the current node and update the lazy[] array for children nodes.
     * 3. If the interval represented by current node overlaps with the interval to update,
     *      then update the nodes as the earlier update function
     *
     * @param start
     * @param end
     * @param queryStart
     * @param queryEnd
     * @param diff
     * @param idxSegTree
     */
    public void updateRangeWithLazy(int start, int end, int queryStart, int queryEnd, int diff, int idxSegTree) {
        // check whether lazyArr array need to be updated
        if (this.lazyArr[idxSegTree] != 0) {
            this.segTree[idxSegTree] += this.lazyArr[idxSegTree];

            if (start != end) {
                this.lazyArr[2 * idxSegTree + 1] = this.lazyArr[idxSegTree];
                this.lazyArr[2 * idxSegTree + 2] = this.lazyArr[idxSegTree];
            }

            // reset value of lazyArr[idxSegTree] to prepare for update again
            lazyArr[idxSegTree] = 0;
        }

        if (start > queryEnd || end < queryStart) {
            return;
        }

        if (queryStart <= start && end <= queryEnd) {
            this.segTree[idxSegTree] += diff;

            if (start != end) {
                this.lazyArr[2 * idxSegTree + 1] += diff;
                this.lazyArr[2 * idxSegTree + 2] += diff;
            }

            return;
        }

        // overlap
        int mid = this.getIndexOfMid(start, end);
        this.updateRangeWithLazy(start, mid, queryStart, queryEnd, diff, 2 * idxSegTree + 1);
        this.updateRangeWithLazy(mid + 1, end, queryStart, queryEnd, diff, 2 * idxSegTree + 2);

        this.segTree[idxSegTree] = Math.min(this.segTree[2 * idxSegTree + 1],
                                            this.segTree[2 * idxSegTree + 2]);
    }

    public void updateRange(int idxSegTree, int start, int end, int qStart, int qEnd, int diff) {
        // outside
        if (qStart > end || qEnd < start) {
            return;
        }

        // leaf node
        if (start == end) {
            this.segTree[idxSegTree] += diff;
            return;
        }

        // overlap
        int mid = this.getIndexOfMid(start, end);
        this.updateRange(2 * idxSegTree + 1, start, mid, qStart, qEnd, diff);
        this.updateRange(2 * idxSegTree + 2, mid + 1, end, qStart, qEnd, diff);

        this.segTree[idxSegTree] = Math.min(this.segTree[2 * idxSegTree + 1],
                                            this.segTree[2 * idxSegTree + 2]);
    }

    public int query(int qStart, int qEnd) {
        if (qStart < 0 || qEnd >= this.arr.length) {
            return Integer.MAX_VALUE;
        }

        return this.query(0, 0, this.arr.length - 1, qStart, qEnd);
    }

    public int queryWithLazy(int qStart, int qEnd) {
        if (qStart < 0 || qEnd >= this.arr.length) {
            return Integer.MAX_VALUE;
        }

        return this.queryWithLazy(0, 0, this.arr.length - 1, qStart, qEnd);
    }

    private int queryWithLazy(int idxSegTree, int start, int end, int qStart, int qEnd) {
        // check whether the element in range need to be updated or not
        if (this.lazyArr[idxSegTree] != 0) {
            this.segTree[idxSegTree] += this.lazyArr[idxSegTree];

            if (start != end) {
                this.lazyArr[2 * idxSegTree + 1] += this.lazyArr[idxSegTree];
                this.lazyArr[2 * idxSegTree + 2] += this.lazyArr[idxSegTree];
            }
        }

        // outside
        if (end < qStart || start > qEnd) {
            return Integer.MAX_VALUE;
        }

        // inside: qStart --- start --- end --- qEnd
        if (qStart <= start && end <= qEnd) {
            return this.segTree[idxSegTree];
        }

        // overlap
        int mid = this.getIndexOfMid(start, end);
        int minLeft = this.queryWithLazy(2 * idxSegTree + 1, start, mid, qStart, qEnd);
        int minRight = this.queryWithLazy(2 * idxSegTree + 2, mid + 1, end, qStart, qEnd);

        return Math.min(minLeft, minRight);
    }

    private int query(int idxSegTree, int start, int end, int qStart, int qEnd) {
        // outside of query index
        if (start > qEnd || end < qEnd) {
            return -1;
        }

        // inside of query index: qStart --- start --- end --- qEnd
        if (qStart <= start && end <= qEnd) {
            return this.segTree[idxSegTree];
        }

        // overlap
        int mid = this.getIndexOfMid(start, end);
        int minLeft = this.query(2 * idxSegTree + 1, start, mid, qStart, qEnd);
        int minRight = this.query(2 * idxSegTree + 2, mid + 1, end, qStart, qEnd);

        return Math.min(minLeft, minRight);
    }

    private void updateAt(int idxSegTree, int start, int end, int idxOrigin, int value) {
        if (idxOrigin < start || idxOrigin > end) {
            return;
        }

        if (start == end) {
            this.segTree[idxSegTree] += value;
            return;
        }

        int mid = this.getIndexOfMid(start, end);
        this.updateAt(2 * idxSegTree + 1, start, mid, idxOrigin, value);
        this.updateAt(2 * idxSegTree + 2, mid + 1, end, idxOrigin, value);

        this.segTree[idxSegTree] = Math.min(this.segTree[2 * idxSegTree + 1],
                                            this.segTree[2 * idxSegTree + 2]);
    }

    private void construct(int idxSegTree, int start, int end) {
        if (start == end) {
            this.segTree[idxSegTree] = arr[start];
            return;
        }

        int mid = this.getIndexOfMid(start, end);
        this.construct(2 * idxSegTree + 1, start, mid);
        this.construct(2 * idxSegTree + 2, mid + 1, end);

        this.segTree[idxSegTree] = Math.min(this.segTree[2 * idxSegTree + 1],
                                            this.segTree[2 * idxSegTree + 2]);
    }

    private int getMaxSize(int length) {
//        int height = (int) Math.ceil(this.log(length));
//        int size = 2 * (int) (Math.pow(2, height)) - 1;
//
//        return size;
        return 4 * length;
    }

    private int getIndexOfMid(int start, int end) {
        return start + (end - start) / 2;
    }

    private int log(int n) {
        return (int) (Math.log(n) / Math.log(2));
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 7, 9, 11};
        int qStart = 1;
        int qEnd = 5;

        MinSegmentTree solution = new MinSegmentTree(nums);
        solution.updateAt(0, 5);

        System.out.println(solution.queryWithLazy(qStart, qEnd));
        System.out.println(solution.query(qStart, qEnd));
    }
}
