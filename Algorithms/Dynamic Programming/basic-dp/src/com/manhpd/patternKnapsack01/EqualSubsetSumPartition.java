package com.manhpd.patternKnapsack01;

import java.util.*;

/**
 * Given a set of positive numbers, find if we can partition it into two subsets such that the sum of elements in both the subsets is equal.
 *
 * Input: {1, 2, 3, 4}
 * Output: True
 * Explanation: The given set can be partitioned into two subsets with equal sum: {1, 4} & {2, 3}
 *
 * Input: {1, 1, 3, 4, 7}
 * Output: True
 * Explanation: The given set can be partitioned into two subsets with equal sum: {1, 3, 4} & {1, 7}
 *
 * Input: {2, 3, 4, 6}
 * Output: False
 * Explanation: The given set cannot be partitioned into two subsets with equal sum.
 *
 */
public class EqualSubsetSumPartition {

    public static void main(String[] args) {
        // Example 1
//        int[] nums = {1, 2, 3, 4};
//        boolean res = true;

        // Example 2
//        int[] nums = {1, 1, 3, 4, 7};
//        boolean res = true;

        // Example 3
//        int[] nums = {2, 3, 4, 6};
//        boolean res = false;

        // Example 4
        int[] nums = {2, 12, 4, 6};
        boolean res = true;

//        boolean canPartition = canPartitionV1(nums);
//        boolean canPartition = canPartitionV2(nums);
//        boolean canPartition = canPartitionV3(nums);
//        boolean canPartition = canPartitionV4(nums);
//        boolean canPartition = canPartitionV5(nums);
//        boolean canPartition = canPartitionV6(nums);
//        boolean canPartition = canPartitionV7(nums);
//        boolean canPartition = canPartitionV8(nums);
        boolean canPartition = canPartitionV9(nums);

        System.out.println(canPartition);
    }

    /**
     * Using recursion version to solve it.
     *
     * @param num
     * @return
     */
    public static boolean canPartitionV1(int[] num) {
        if (num == null || num.length == 0) {
            return false;
        }

        int sum = Arrays.stream(num).sum();
        if ((sum & 1) != 0) {
            return false;
        }

        boolean canPartition = canPartitionV1(num, 0, sum/2);
        return canPartition;
    }

    public static boolean canPartitionV1(int[] num, int currentIndex, int target) {
        if (currentIndex >= num.length || target < 0) {
            return false;
        }

        if (target == 0) {
            return true;
        }

        boolean isAdded = false;
        boolean isSkipped = false;
        if (num[currentIndex] <= target) {
            isAdded = canPartitionV1(num, currentIndex + 1, target - num[currentIndex]);
        }

        isSkipped = canPartitionV1(num, currentIndex + 1, target);
        return isAdded || isSkipped;
    }

    /**
     * Use Dynamic Programming with Memoization
     *
     * @param num
     * @return
     */
    public static boolean canPartitionV2(int[] num) {
        if (num == null || num.length == 0) {
            return false;
        }

        int sum = Arrays.stream(num).sum();
        if ((sum & 1) != 0) {
            return false;
        }

        Boolean[][] dp = new Boolean[num.length][(sum/2) + 1];

        boolean canPartition = canPartitionV2(num, dp, 0, sum/2);
        return canPartition;
    }

    private static boolean canPartitionV2(int[] num, Boolean[][] dp, int idx, int halfSum) {
        if (idx >= num.length || halfSum < 0) {
            return false;
        }

        if (halfSum == 0) {
            dp[idx][halfSum] = true;
            return true;
        }

        if (dp[idx][halfSum] != null) {
            return dp[idx][halfSum];
        }

        boolean isAdded = false;
        boolean isSkipped = false;

        if (num[idx] <= halfSum) {
            isAdded = canPartitionV2(num, dp, idx + 1, halfSum - num[idx]);
        }

        isSkipped = canPartitionV2(num, dp, idx + 1, halfSum);
        dp[idx][halfSum] = isAdded || isSkipped;

        return dp[idx][halfSum];
    }

    /**
     * Using Dynamic Programming with Tabulation
     *
     * @param num
     * @return
     */
    public static boolean canPartitionV3(int[] num) {
        if (num == null || num.length == 0) {
            return false;
        }

        int sum = Arrays.stream(num).sum();
        if ((sum & 1) != 0) {
            return false;
        }

        int halfSum = sum/2;
        boolean[][] dp = new boolean[num.length][halfSum + 1];

        // when sum = 0, we always have a subset's sum = 0 --> true
        for (int i = 1; i < num.length; ++i) {
                dp[i][0] = true;
        }

        // If the nums array that has only one element, the value at dp's index will be true
        // if its value = sum
        for (int i = 1; i <= halfSum; ++i) {
            dp[0][i] = (num[0] == i ? true : false);
        }

        boolean res = false;
        for (int i = 1; i < num.length; ++i) {
            for (int j = 1; j <= halfSum; ++j) {
                res = false;

                if (num[i] <= j) {
                    res = dp[i - 1][j - num[i]];
                }

                dp[i][j] = res || dp[i - 1][j];
            }
        }

        return dp[num.length - 1][halfSum];
    }

    /**
     * Using the brute-force solution with HashSet to
     * save all the sum and prevent the duplications.
     *
     * @param num
     * @return
     */
    public static boolean canPartitionV4(int[] num) {
        if (num == null || num.length == 0) {
            return false;
        }

        int sum = Arrays.stream(num).sum();
        if ((sum & 1) != 0) {
            return false;
        }

        int halfSum = sum / 2;
        HashSet<Integer> dp = new HashSet<>();
        dp.add(0);

        for (int i = 0; i < num.length; ++i) {
            HashSet<Integer> tmp = new HashSet<>();

            for (int val : dp) {
                tmp.add(val);
                if (val + num[i] == halfSum) {
                    return true;
                }

                tmp.add(val + num[i]);
            }

            dp = tmp;
        }

        return false;
    }

    /**
     * Using BitSet
     *
     * @param num
     * @return
     */
    public static boolean canPartitionV5(int[] num) {
        if (num == null || num.length == 0) {
            return false;
        }

        int sum = Arrays.stream(num).sum();
        if (sum % 2 != 0) {
            return false;
        }

        BitSet bitSet = new BitSet();
        bitSet.set(0);

        for (int n : num) {
            bitSet.or(shiftLeft(bitSet, n));
            bitSet.set(0);
        }

        return bitSet.get(sum >> 1);
    }

    private static BitSet shiftLeft(BitSet bitSet, int shift) {
        if (shift == 0) {
            return bitSet;
        }

        int d = shift >> 6;
        int r = shift & 63;

        long[] arr = bitSet.toLongArray();
        int arrLen = arr.length;
        int resLen = arrLen + d + 1;

        long[] res = new long[resLen];

        for (int i = d; i < resLen; ++i) {
            if (i - d < arrLen) {
                res[i] = arr[i - d] << r;
            }

            if (i - d - 1 >= 0 && r > 0) {
                res[i] |= arr[i - d - 1] >>> 64 - r;
            }
        }

        return BitSet.valueOf(res);
    }

    /**
     * Using Dynamic Programming with Bitmask
     * Bitmask dp will save all the subset sum of the current array
     *
     * @param num
     * @return
     */
    public static boolean canPartitionV6(int[] num) {
        int total = 0;
        int dp = 1;

        for (int i = 0; i < num.length; ++i) {
            System.out.println("Index = " + i + ", num[i] = " + num[i]);
            total += num[i];
            dp |= dp << num[i];

            System.out.println("dp = " + dp + ", binary format of dp = " + Integer.toBinaryString(dp));
        }

        int half = total / 2;
        return (total % 2 == 0) && (dp & (1 << half)) != 0;
    }

    /**
     * Using brute force with Bit Masking
     *
     * @param num
     * @return
     */
    public static boolean canPartitionV7(int[] num) {
        if (num == null || num.length == 0) {
            return false;
        }

        int sum = Arrays.stream(num).sum();
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        int maxMask = 1 << num.length;

        for (int mask = 0; mask < maxMask; ++mask) {
            int subsetSum = 0;

            for (int i = 0; i < num.length; ++i) {
                if ((mask & (1 << i)) != 0) {
                    subsetSum += num[i];
                }
            }

            if (subsetSum == target) {
                return true;
            }
        }

        return false;
    }

    /**
     * Using backtracking to show all elements
     *
     * @param nums
     * @return
     */
    public static boolean canPartitionV8(int[] nums) {
        int sum = 0;
        int dp = 1;

        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            dp |= dp << nums[i];
        }

        int target = sum / 2;
        if (((dp & (1 << target)) == 0)) {
            return false;
        }

        List<Integer> subset = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; --i) {
            if (target >= nums[i] && (dp & (1 << (target - nums[i]))) != 0 ) {
                subset.add(nums[i]);
                target -= nums[i];
            }

            if (target == 0) {
                break;
            }
        }

        System.out.println("subset: " + subset);
        return true;
    }

    /**
     * Using a buffer to save subset
     *
      * @param nums
     * @return
     */
    public static boolean canPartitionV9(int[] nums) {
        int dp = 1;
        int sum = Arrays.stream(nums).sum();
        int target = sum / 2;

        int maxSum = sum + 1;
        int[] trace = new int[maxSum];
        Arrays.fill(trace, -1); // -1 biểu thị chưa có số nào tạo ra tổng này

        for (int num : nums) {
            for (int i = target; i >= num; --i) { // Duyệt ngược để tránh ghi đè sai
                if ((dp & (1 << (i - num))) != 0 && trace[i] == -1) {
                    dp |= (1 << i);
                    trace[i] = num;
                }
            }
        }

        if (trace[target] == -1) {
            return false;
        }

        List<Integer> subset = new ArrayList<>();
        while (target > 0) {
            subset.add(trace[target]);
            target -= trace[target];
        }

        System.out.println("subset: " + subset);
        return true;
    }
}
