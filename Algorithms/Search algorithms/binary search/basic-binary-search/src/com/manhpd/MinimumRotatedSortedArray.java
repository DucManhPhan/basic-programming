package com.manhpd;

/**
 * Suppose an array that was sorted in ascending order is rotated. For example, the array `nums = [0,1,2,4,5,6,7]` might become:
 * - `[4,5,6,7,0,1,2]`, if it is rotated 4 times.
 * - `[0,1,2,4,5,6,7]`, if it is rotated 7 times.
 *
 * Notice the rotation of an array `[nums[0], nums[1], nums[2], ..., nums[n-1]]` `1` time results in the array `[nums[n-1], nums[0], nums[1], nums[2], ..., nums[n-2]]`.
 *
 * Given sorted and rotated array `nums`, return the minimum element in the array. Constraints:
 * - `1 ≤ nums.length ≤ 5000`.
 * - `−5000 ≤ nums[i] ≤ 5000`.
 * - All of the integers of `nums` are unique.
 * - `nums` is sorted and rotated.
 *
 * Example 1:
 * - Input: `[3,4,5,1,2]`
 * - Output: 1
 * - Explanation: The original array was `[1,2,3,4,5]`, and it was rotated 3 times.
 *
 * Example 2:
 * - Input: `[4,5,6,7,0,1,2]`
 * - Output: 0
 * - Explanation: The original array was `[0,1,2,4,5,6,7]`, and it was rotated 4 times.
 *
 * Example 3:
 * - Input: `[11,13,15,17]`
 * - Output: 11
 * - Explanation: The original array was `[11,13,15,17]`, and it was rotated 4 times.
 *
 */
public class MinimumRotatedSortedArray {

    public static void main(String[] args) {
//        int[] nums = {3, 4, 5, 1, 2};
//        int[] nums = {4, 5, 6, 7, 0, 1, 2};
//        int[] nums = {11, 13, 15, 17};
//        int[] nums = {5, 1, 2, 3, 4};
        int[] nums = {2, 3, 4, 5, 6, 0, 1};

//        int res = findMinimumElementV1(nums);
//        int res = findMinimumElementV2(nums);
        int res = findMinimumElementV3(nums);
        System.out.println("Result: " + res);
    }

    /**
     * Using Binary Search based on the upward segments and downward segments.
     * Condition: comparing mid and left.
     *
     * @param nums
     * @return
     */
    private static int findMinimumElementV3(int[] nums) {
        int pivot = findPivot(nums);
        return Math.min(nums[0], nums[pivot]);
    }

    private static int findPivot(int[] nums) {
        int left = 0;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid - 1] > nums[mid]) {
                return mid;
            } else if (nums[left] < nums[mid]) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }

    /**
     * Use Binary Search with comparing mid and right.
     *
     * @param nums
     * @return
     */
    private static int findMinimumElementV2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return Math.min(nums[left], nums[right]);
    }

    /**
     * Using Bitonic point in nums array
     * --> Wrong way when applying this example: [5, 1, 2, 3, 4]
     * It is not bitonic point when taking care it.
     *
     * @param nums
     * @return
     */
    private static int findMinimumElementV1(int[] nums) {
        int bitonicPoint = findBitonicPoint(nums);

        if (bitonicPoint == nums.length - 1) {
            return nums[0];
        }

        return nums[bitonicPoint + 1];
    }

    private static int findBitonicPoint(int[] nums) {
        int left = 0;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (mid < nums.length - 1 && nums[mid] > nums[mid - 1]
                    && nums[mid] > nums[mid + 1]) {
                return mid;
            }

            if (nums[mid] > nums[mid - 1]) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }

}
