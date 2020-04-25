package com.manhpd;

/**
 * Write an efficient program to count number of 1s in binary representation of an integer.
 *
 * Input : n = 6
 * Output : 2
 * Binary representation of 6 is 110 and has 2 set bits
 *
 * Input : n = 13
 * Output : 3
 * Binary representation of 13 is 1101 and has 3 set bits
 *
 */
public class CountSetBitsInAnInteger {

    public static void main(String[] args) {
        int n = 6;
        int count = countSetBitsUsingString(n);
        System.out.println(count);
    }

    /**
     * Convert an integer to string. Iterate this string and count 1
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param n
     * @return
     */
    public static int countSetBitsUsingString(int n) {
        String bits = Integer.toBinaryString(n);
        System.out.println(bits);
        int count = 0;

        for (int i = 0; i < bits.length(); ++i) {
            if (bits.charAt(i) == '1') {
                ++count;
            }
        }

        return count;
    }

    public static int countSetBits(int n) {
        return 0;
    }

}
