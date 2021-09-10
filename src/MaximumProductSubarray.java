/*
Given an integer array nums, find a contiguous non-empty subarray
within the array that has the largest product, and return the product.

It is guaranteed that the answer will fit in a 32-bit integer.

A subarray is a contiguous subsequence of the array.

https://leetcode.com/problems/maximum-product-subarray/
 */

public class MaximumProductSubarray {

    public static void main(String[] args) {

    }

    /**
     * Solved using DP.
     * dp[i][0] means the largest positive ending at i.
     * dp[i][1] means the smallest negative ending at i.
     * If nums[i] > 0:
     *      dp[i][0] = max(dp[i-1][0]*nums[i], nums[i])
     *      dp[i][1] = min(dp[i-1][1]*nums[i], 0)
     * @param nums the array
     * @return the max product
     */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int ans = nums[0];
        // dp[i][0] means the largest positive
        // dp[i][1] means the largest negative
        int[][] dp = new int[n][2];
        if (nums[0] > 0) {
            dp[0][0] = nums[0];
        } else if (nums[0] < 0) {
            dp[0][1] = nums[0];
        }
        for (int i = 1; i < n; i++) {
            int pos = 0, neg = 0;
            if (nums[i] > 0) {
                pos = Math.max(nums[i], nums[i] * dp[i - 1][0]);
                neg = nums[i] * dp[i - 1][1];
            } else if (nums[i] < 0) {
                pos = nums[i] * dp[i - 1][1];
                neg = Math.min(nums[i], nums[i] * dp[i - 1][0]);
            }
            ans = Math.max(ans, pos);
            dp[i][0] = pos;
            dp[i][1] = neg;
        }
        return ans;
    }
}
