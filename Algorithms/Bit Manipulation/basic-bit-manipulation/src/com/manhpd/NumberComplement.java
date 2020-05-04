package com.manhpd;

/**
 * Given a positive integer, output its complement number.
 * The complement strategy is to flip the bits of its binary representation.
 *
 * Constraints:
 * The given integer is guaranteed to fit within the range of a 32-bit signed integer.
 * You could assume no leading zero bit in the integer’s binary representation.
 * This question is the same as 1009: https://leetcode.com/problems/complement-of-base-10-integer/
 *
 * Example 1:
 * Input: 5
 * Output: 2
 * Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010.
 * So you need to output 2.
 *
 * Example 2:
 * Input: 1
 * Output: 0
 * Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0.
 * So you need to output 0.
 *
 */
public class NumberComplement {

    public static void main(String[] args) {
        int num = 5;
        int res = findComplement(num);
        System.out.println(res);
    }

    public static int findComplement(int num) {
        String bits = Integer.toBinaryString(num);
        StringBuilder sb = new StringBuilder();

        for (char c : bits.toCharArray()) {
            if (c == '1') {
                sb.append('0');
            } else {
                sb.append('1');
            }
        }

        return Integer.valueOf(sb.toString(), 2);
    }

}
