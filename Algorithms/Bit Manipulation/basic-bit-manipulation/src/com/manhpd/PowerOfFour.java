package com.manhpd;

/**
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 *
 * Example 1:
 *
 * Input: 16
 * Output: true
 * Example 2:
 *
 * Input: 5
 * Output: false
 * Follow up: Could you solve it without loops/recursion?
 *
 */
public class PowerOfFour {

    public static void main(String[] args) {
        int num = 6;
        boolean res = isPowerOfFour(num);
        System.out.println(res);
    }

    /**
     * Using loop
     *
     * @param num
     * @return
     */
    public static boolean isPowerOfFour(int num) {
        int remained = 0;
        while (num != 0) {
            remained = num % 4;
            num = num / 4;

            if (remained != 0) {
                return false;
            } else if (num == 1) {
                return true;
            }
        }

        return false;
    }

}
