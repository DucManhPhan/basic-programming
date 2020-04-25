package com.manhpd;

/**
 * Given a number, find the most significant bit number which is set bit and which is in power of two
 *
 * Input : 10
 * Output : 8
 * Binary representation of 10 is 1010
 * The most significant bit corresponds
 * to decimal number 8.
 *
 * Input : 18
 * Output : 16
 */
public class FindMostSignificantSetBit {

    public static void main(String[] args) {
        int n = 18;
        int res = getPosMSBSetBitIterative(n);
//        int res = getPosMSBSetBitUsingLogMethod(n);

        System.out.println(res);
    }

    /**
     * Using iterative to divide n for 2. Increase num to 1.
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param n
     * @return
     */
    public static int getPosMSBSetBitIterative(int n) {
        int position = -1;

        while (n != 0) {
            n = n / 2;
            ++position;
        }

        return position;
    }

    public static int getPosMSBSetBitUsingLogMethod(int n) {
        int k = (int)(Math.log(n) / Math.log(2));
        return k;
    }

}
