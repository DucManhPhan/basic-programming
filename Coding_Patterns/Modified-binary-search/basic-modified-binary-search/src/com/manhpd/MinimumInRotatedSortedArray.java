package com.manhpd;

/**
 * Ref: https://www.educative.io/courses/binary-search-coding-interview/mED8kxJWRLE
 * <p>
 * Suppose an array that was sorted in ascending order is rotated. For example, the array nums = [0,1,2,4,5,6,7] might become:
 * + [4,5,6,7,0,1,2], if it is rotated 4 times.
 * + [0,1,2,4,5,6,7], if it is rotated 7 times.
 * <p>
 * Notice the rotation of an array [nums[0], nums[1], nums[2], ..., nums[n-1]] 1 time results in the array [nums[n-1], nums[0], nums[1], nums[2], ..., nums[n-2]].
 * <p>
 * Given sorted and rotated array nums, return the minimum element in the array.
 * <p>
 * Constraints:
 * + -1 <= nums.length <= 5000
 * + -5000 <= nums[i] <= 5000
 * + All of the integers of nums are unique
 * + nums is sorted and rotated
 * <p>
 * Example 1:
 * Input: [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5], and it was rotated 3 times.
 * <p>
 * Example 2:
 * Input: [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7], and it was rotated 4 times.
 * <p>
 * Example 3:
 * Input: [11,13,15,17]
 * Output: 11
 * Explanation: The original array was [11,13,15,17], and it was rotated 4 times.
 */
public class MinimumInRotatedSortedArray {

    public static void main(String[] args) {
//        int[] nums = {3, 4, 5, 1, 2};
//        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int[] nums = {11, 13, 15, 17};

        int res = findMinimumElement(nums);
        System.out.println("Result: " + ((res != -1) ? nums[res] : -1));

        System.out.println("Result: " + findMin(nums));
    }

    public static int findMinimumElement(int[] nums) {
        int bitonicPoint = findBitonicPoint(nums);

        if (bitonicPoint == nums.length - 1) {
            return 0;
        }

        return bitonicPoint + 1;
    }

    /**
     * We can't use the bitonic array into this issue. Because our sorted array was rotated.
     * Then it doesn't have some properties of bitonic array.
     *
     * So we will use the findPivot() method as our solution.
     *
     * @param nums
     * @return
     */
    public static int findBitonicPoint(int[] nums) {
        int left = 0;
        int right = nums.length;
        int bitonicPoint = -1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (mid > 0 && mid < nums.length - 1 && nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            }

            if (mid < nums.length - 1) {
                if (nums[mid] < nums[mid + 1]) {
                    left = mid;
                } else {
                    right = mid;
                }
            } else {
                left = mid;
            }
        }

        return left;
    }

    public static int findPivot(int[] nums) {
        // the initial value for left index is 0
        int left = 0;
        // the initial value for right index is the number of elements in the array
        int right = nums.length;
        // left + 1 >= right will finish while loop
        while (left + 1 < right) {
            int mid = (right + left) / 2;

            if (nums[mid - 1] > nums[mid]) {
                // mid is the index to answer
                return mid;
            } else if (nums[left] < nums[mid]) {
                // there is no sense to search in the left half of the array
                left = mid;
            } else {
                // there is no sense to search in the right half of the array
                right = mid;
            }
        }
        // left is the answer
        return left;
    }

    public static int findMin(int[] nums) {
        int pivot = findPivot(nums);

        return Math.min(nums[0], nums[pivot]);
    }

}
