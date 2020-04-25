package com.manhpd;


/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 *
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 *
 * Ex1: Input: [1,3,4,2,2]
 *      Output: 2
 *
 * Ex2: Input: [3,1,3,4,2]
 *      Output: 3
 *
 */
public class FindDuplicateNumber {

    public static void main(String[] args) {
        int [] nums = {1, 2, 3, 4, 2, 4, 3, 5};
//        int [] nums = {2, 2, 2, 2, 2};
//        int result = findDuplicate(nums);
        int result = findDuplicateII(nums);

        System.out.println("Result: " + result);
    }

    /**
     * Change the sign of each element in an array. Then, find the element that has value < 0
     *
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums) {
        int duplicate = -1;

        for (int i = 0; i < nums.length; ++i) {
            int val = (nums[i] < 0 ? -nums[i] : nums[i]);
            if (nums[val - 1] >= 0) {
                nums[val - 1] = -nums[val - 1];
            } else {
                duplicate = val;
                break;
            }
        }

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] < 0) {
                nums[i] = -nums[i];
            }
        }

        return duplicate;
    }

    /**
     *
     *
     * @param nums
     * @return
     */
    public static int findDuplicateII(int[] nums) {
        int len = nums.length;
        int element = -1;

        for (int i = 0; i < len; ++i) {
            int item = Math.abs(nums[i]);
            if (nums[item] > 0) {
                nums[item] = -nums[item];
            } else {
                System.out.println(item + ", ");
                element = item;
//                return item;
            }
        }

        return element;
    }

}
