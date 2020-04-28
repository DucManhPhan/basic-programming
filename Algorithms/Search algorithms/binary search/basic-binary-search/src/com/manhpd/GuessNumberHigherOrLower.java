package com.manhpd;

/**
 * We are playing the Guess Game. The game is as follows:
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 *
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 * -1 : My number is lower
 *  1 : My number is higher
 *  0 : Congrats! You got it!
 *
 * Input: n = 10, pick = 6
 * Output: 6
 *
 */
public class GuessNumberHigherOrLower {

    private static int pick = -1;

    public static void main(String[] args) {
        int n = 10;
        pick = 6;

        int res = guessNumber(n);
        System.out.println(res);
    }

    public static int guess(int num) {
        int diff = num - pick;
        if (diff == 0) {
            return 0;
        } else if (diff > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public static int guessNumber(int n) {
        int left = 1;
        int right = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int guessResult = guess(mid);

            if (guessResult == 0) {
                return mid;
            } else if (guessResult > 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

}
