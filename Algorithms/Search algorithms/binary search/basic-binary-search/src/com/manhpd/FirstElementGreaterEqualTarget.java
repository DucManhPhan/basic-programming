package com.manhpd;

/**
 * Ref: https://programmer.ink/think/general-code-template-for-binary-search-problem.html
 * <p>
 * Find the first element greater than or equal to the target value, and return None if it does not exist
 * <p>
 * Example 1:
 * Input: [1,2,3,4,5,6,6,6,8,9], key = 6
 * Output:
 */
public class FirstElementGreaterEqualTarget {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 6, 6, 8, 9};
        int key = 7;

        int res = binarySearch1st(nums, key);
        System.out.println("Result: " + res);
    }

    public static int binarySearch1st(int[] nums, int target) {
        int blue = -1;
        int red = nums.length;

        while (blue + 1 < red) {
            int mid = blue + (red - blue) / 2;

            if (target <= nums[mid]) {
                red = mid;
            } else {
                blue = mid;
            }
        }

        if (red < nums.length && nums[red] >= target) {
            return red;
        }

        return -1;
    }

}
