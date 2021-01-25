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
    }

    public void construct() {
        this.construct(0, this.arr.length - 1, 0);
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

        int diff = value = arr[idxOrigin];
        arr[idxOrigin] = value;

        this.update(0, 0, this.arr.length - 1, idxOrigin, value);
    }

    public void updateRange(int start, int end, int queryStart, int queryEnd, int diff, int idxSegTree) {
        if (this.lazyArr[idxSegTree] != 0) {
            this.segTree[idxSegTree] += this.lazyArr[idxSegTree];

            if (start != end) {
                this.lazyArr[2 * idxSegTree + 1] = this.lazyArr[idxSegTree];
                this.lazyArr[2 * idxSegTree + 2] = this.lazyArr[idxSegTree];
            }

            // reset value of lazyArr[idxSegTree] t o prepare for update again
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

        int mid = this.getIndexOfMid(start, end);
        this.updateRange(start, mid, queryStart, queryEnd, diff, 2 * idxSegTree + 1);
        this.updateRange(mid + 1, end, queryStart, queryEnd, diff, 2 * idxSegTree + 2);

        this.segTree[idxSegTree] = Math.min(this.segTree[2 * idxSegTree + 1],
                                            this.segTree[2 * idxSegTree + 2]);
    }

    private void update(int idxSegTree, int start, int end, int idxOrigin, int value) {
        if (idxOrigin < start || idxOrigin > end) {
            return;
        }

        if (start == end) {
            this.segTree[idxSegTree] += value;
            return;
        }

        int mid = this.getIndexOfMid(start, end);
        update(2 * idxSegTree + 1, start, mid, idxOrigin, value);
        update(2 * idxSegTree + 2, mid + 1, end, idxOrigin, value);

        this.segTree[idxSegTree] = Math.min(this.segTree[2 * idxSegTree + 1],
                                            this.segTree[2 * idxSegTree + 2]);
    }

    private void construct(int start, int end, int indexOfSegTree) {
        if (start == end) {
            this.segTree[indexOfSegTree] = arr[start];
            return;
        }

        int mid = this.getIndexOfMid(start, end);
        this.construct(start, mid, 2 * indexOfSegTree + 1);
        this.construct(mid + 1, end, 2 * indexOfSegTree + 2);

        this.segTree[indexOfSegTree] = Math.min(this.segTree[2 * indexOfSegTree + 1],
                                                this.segTree[2 * indexOfSegTree + 2]);
    }

    private int getMaxSize(int length) {
        int height = (int) Math.ceil(this.log(length));
        int size = 2 * (int) (Math.pow(2, height)) - 1;

        return size;
    }

    private int getIndexOfMid(int start, int end) {
        return start + (end - start) / 2;
    }

    private int log(int n) {
        return (int) (Math.log(n) / Math.log(2));
    }
}
