package com.manhpd;


/**
 * Ref: https://programmer.ink/think/general-code-template-for-binary-search-problem.html
 * <p>
 * Find the last element equal to the target value, and return None if it does not exist
 * <p>
 * Example 1:
 * Input: [1,2,3,4,5,6,6,6,7,8], key = 6
 * Output: 5
 */
public class LastElementEqualTarget {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 6, 6, 7, 8};
        int key = 6;

        int res = searchLastElement(nums, key);
        System.out.println("Result: " + res);
    }

    /**
     * Using the template #3 of Binary Search algorithm
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchLastElement(int[] nums, int target) {
        int blue = -1;
        int red = nums.length;

        while (blue + 1 != red) {
            int mid = blue + (red - blue)/2;

            if (target < nums[mid]) {
                red = mid;
            } else {
                blue = mid;
            }
        }

        if (blue < nums.length && nums[blue] == target) {
            return blue;
        }

        return -1;
    }

}
