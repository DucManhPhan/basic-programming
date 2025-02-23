package org.manhpd;

/**
 * In a single-player jump game, the player starts at one end of a series of squares, with the goal of reaching the last square.
 * At each turn, the player can take up to s steps towards the last square, where s is the value of the current square.
 *
 * For example, if the value of the current square is 3, the player can take either 3 steps, or 2 steps, or
 * 1 step in the direction of the last square.
 *
 * The player cannot move in the opposite direction, that is, away from the last square.
 *
 * You have been tasked with writing a function to validate whether a player can win a given game or not.
 * You’ve been provided with the nums integer array, representing the series of squares.
 * The player starts at the first index and, following the rules of the game, tries to reach the last index.
 *
 * If the player can reach the last index, your function returns TRUE; otherwise, it returns FALSE.
 *
 * Constraints:
 * 1<= nums.length <= 10^3
 * 0 <= nums[i] <= 10^3
 *
 */
public class JumpGame {

    public static void main(String[] args) {
        // Example 1
//        int nums[] = {2, 3, 1, 1, 4};
//        boolean expected = true;

        // Example 2
        int nums[] = {3, 2, 1, 0, 4};
        boolean expected = false;

        // Example 3
//        int nums[] = {2, 3, 1, 1, 9};
//        boolean expected = true;

        // Example 4
//        int nums[] = {4, 0, 0, 0, 4};
//        boolean expected = true;

        // Example 5
//        int nums[] = {3, 2, 2, 0, 1, 4};
//        boolean expected = true;

//        boolean res = jumpGameV1(nums);
//        boolean res = jumpGameV2(nums);
//        boolean res = jumpGameV3(nums);
        boolean res = jumpGameV4(nums);
        System.out.println("Result = " + res + ", expected: " + expected);
    }

    /**
     * Use brute force solution
     *
     * @param nums
     * @return
     */
    public static boolean jumpGameV1(int[] nums) {
        return backtrackingJumpGame(nums, 0);
    }

    private static boolean backtrackingJumpGame(int[] nums, int currentIdx) {
        // base case
        if (currentIdx >= nums.length - 1) {
            return true;
        }

        // Way 1: Use while loop
//        int step = nums[currentIdx];
//        while (step > 0) {
//            int newIdx = currentIdx + step;
//
//            if (backtrackingJumpGame(nums, newIdx)) {
//                return true;
//            }
//
//            --step;
//        }

        // Way 2: Use for loop to jumps
        for (int i = 1; i <= nums[i]; ++i) {
            if (backtrackingJumpGame(nums, currentIdx + i)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Use greedy technique by iterate from the last element of an array
     *
     * @param nums
     * @return
     */
    public static boolean jumpGameV2(int[] nums) {
        int targetNumIdx = nums.length - 1;

        for (int i = nums.length - 2; i >= 0; --i) {
            if (targetNumIdx <= i + nums[i]) {
                targetNumIdx = i;
            }
        }

        if (targetNumIdx == 0) {
            return true;
        }

        return false;
    }

    /**
     * Use greedy technique by iterating from the beginning of an array
     *
     * @param nums
     * @return
     */
    public static boolean jumpGameV3(int[] nums) {
        int farthestIndex = 0;

        for (int i = 0; i < nums.length; ++i) {
            if (farthestIndex < i) {
                return false;
            }

            farthestIndex = Math.max(farthestIndex, i + nums[i]);
            if (farthestIndex >= nums.length - 1) {
                return true;
            }
        }

        return true;
    }

    /**
     * Improve the backtracking method by using DP - memoization
     *
     */
    public static boolean jumpGameV4(int[] nums) {
        boolean[] memoizationBuffer = new boolean[nums.length];
        return canJumpMemoization(nums, 0, memoizationBuffer);
    }

    private static boolean canJumpMemoization(int[] nums, int idx, boolean[] memoizationBuffer) {
        if (memoizationBuffer[idx] || idx >= nums.length - 1) {
            return true;
        }

        for (int i = 1; i <= nums[idx]; ++i) {
            if (canJumpMemoization(nums, idx + i, memoizationBuffer)) {
                memoizationBuffer[idx] = true;
                return true;
            }
        }

        memoizationBuffer[idx] = false;
        return false;
    }

    /**
     * Using Dynamic programming based on tabulation
     *
     * @param nums
     * @return
     */
    public static boolean jumpGame5(int[] nums) {



        return false;
    }
}
