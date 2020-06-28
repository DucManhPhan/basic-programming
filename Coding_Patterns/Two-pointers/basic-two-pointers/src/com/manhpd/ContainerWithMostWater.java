package com.manhpd;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 *
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 *
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int res = maxArea(heights);

        System.out.println(res);
    }

    /**
     * Using brute force solution
     * 
     * Time complexity: O(n^2)
     * Space complexity: O(1)
     * 
     * @param height
     * @return
     */
    public static int maxAreaBruteForce(int[] height) {
    	int len = height.length;
    	int maxWater = Integer.MIN_VALUE;
    	
    	for (int i = 0; i < len; ++i) {
    		for (int j = len - 1; j > i; --j) {
    			int minHeight = Math.min(height[i], height[j]);
    			maxWater = Math.max(maxWater, minHeight * (j - i));
    		}
    	}
    	
        return maxWater;
    }
    
    /**
     * Using two pointer solution
     * 
     * Time complexity: O(n)
     * Space complexity: O(1)
     * 
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
    	int left = 0;
    	int right = height.length - 1;
    	int maxArea = Integer.MIN_VALUE;
    	
    	while (left < right) {
    		maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
    		if (height[left] < height[right]) {
    			++left;
    		} else {
    			--right;
    		}
    	}
    	
    	return maxArea;
    }

}
