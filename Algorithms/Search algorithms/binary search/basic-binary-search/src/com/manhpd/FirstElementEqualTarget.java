package com.manhpd;

/**
 * Ref: https://programmer.ink/think/general-code-template-for-binary-search-problem.html
 * <p>
 * Find the first element equal to the target value, and return None if it does not exist
 * <p>
 * Example 1:
 * Input: [1,2,3,4,5,6,6,6,7,8], key = 6
 * Output:
 */
public class FirstElementEqualTarget {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 6, 6, 7, 8};
        int key = 6;

//        int result = binarySearchTemplateN3(nums, key);
        int result = binarySearchTemplateN1(nums, key);
        System.out.println("Result: " + result);
    }

    /**
     * Using the binary search template #3
     *
     * @param nums
     * @param key
     * @return
     */
    public static int binarySearchTemplateN3(int[] nums, int key) {
        int blue = -1;
        int red = nums.length;

        while (blue + 1 != red) {
            int mid = blue + (red - blue) / 2;

            if (nums[mid] < key) {
                blue = mid;
            } else {
                red = mid;
            }
        }

        if (red < nums.length && nums[red] == key) {
            return red;
        }

        return 0;
    }

    /**
     * Using the template #1 of Binary Search algorithm
     *
     * @param nums
     * @param key
     * @return
     */
    public static int binarySearchTemplateN1(int[] nums, int key) {
        int left = 0;
        int right = nums.length - 1;
        int pos = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (key < nums[mid]) {
                right = mid - 1;
            } else if (nums[mid] < key) {
                left = mid + 1;
            } else {
                pos = mid;
                right = mid - 1;
            }
        }

        return pos;
    }

}
