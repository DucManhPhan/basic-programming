package com.manhpd;

/**
 * Write a one line function to return position of first 1 from right to left,
 * in binary representation of an Integer.
 *
 * Ex1: Input: 18 = 010010
 * Output: 2
 *
 * Ex2: Input: 19 = 010011
 *  * Output: 1
 *
 */
public class FindPosOfRightMostSetBit {

    public static void main(String[] args) {
        int n = 18;
        int res = getPosRightMostSetBit(n);
        System.out.println(res);
    }

    public static int getPosRightMostSetBit(int n) {
        int m = 1;
        int pos = 1;

        while ((n & m) == 0) {
            m = m << 1;
            ++pos;
        }

        return pos;
    }

}
