package com.manhpd;

/**
 * The search goes through the array from left to right, and the initial jump length is n/2.
 * At each step, the jump length will be halved: first n/4, then n/8, n/16, etc., until finally the length is 1.
 * After the jumps, either the target element has been found or we know that it does not appear in the array.
 * The following code implements the binary stride version:
 *
 */
public class BinarySearchStrideVersion {

    public static void main(String[] args) {
        int[] a = {1, 4, 7, 8, 10};
        int key = 4;

        int pos = binary_stride(a, a.length, key);
        System.out.println(pos);
    }

    public static int binary_stride(int a[], int sz, int needle) {
        int pos = 0;
        for (int stride = sz / 2; stride >= 1; stride /= 2) {
            while (pos + stride < sz && a[pos + stride] <= needle) {
                pos += stride;
            }
        }

        if (a[pos] == needle) return pos;
        return -1;
    }

//    public static int find_crossover_point(int a[], int sz) {
//        int pos = 0;
//        for(int stride = sz/2;stride >= 1;stride /= 2) {
//            while(fn(a[pos+stride]) <= 0) pos += stride;
//        }
//        return pos;
//    }

}
