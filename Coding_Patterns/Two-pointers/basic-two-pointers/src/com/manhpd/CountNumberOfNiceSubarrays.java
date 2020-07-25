package com.manhpd;

public class CountNumberOfNiceSubarrays {

    public static void main(String[] args) {
        int[] nums = {1,1,2,1,1};
        int k = 3;
//        int[] nums = {2,2,2,1,2,2,1,2,2,2};
//        int k = 2;

//        int res = numberOfSubarrays(nums, k);
        int res = numberOfSubarraysSlidingWindow(nums, k);
        System.out.println(res);
    }

    public static int numberOfSubarrays(int[] nums, int k) {
        int numberOfSub = 0;
        for (int sizeSub = k; sizeSub <= nums.length; ++sizeSub) {
            for (int start = 0; start <= nums.length - sizeSub; ++start) {
                int count = k;

                for (int i = start; i < start + sizeSub; ++i) {
                    if (nums[i] % 2 == 1) {
                        --count;
                    }
                }

                if (count == 0) {
                    ++numberOfSub;
                }
            }
        }

        return numberOfSub;
    }

    public static int numberOfSubarraysSlidingWindow(int[] nums, int k) {
        int start = 0, end = 0, len = nums.length;
        int count = 0, oddCount = 0, evenCount = 0;

        while(end < len){

            while(end < len && oddCount < k){
                if(nums[end++] % 2 != 0)  ++oddCount;
            }

            evenCount = 1;
            while(end < len && nums[end] % 2 == 0){
                ++evenCount;
                ++end;
            }

            while(start < len && oddCount == k){
                count += evenCount;
                if(nums[start++] % 2 != 0) --oddCount;
            }
        }

        return count;
    }

}
