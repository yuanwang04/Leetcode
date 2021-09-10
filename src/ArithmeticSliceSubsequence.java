/*
Given an integer array nums, return the number of all the arithmetic subsequences of nums.

A sequence of numbers is called arithmetic if it consists of at least three elements
and if the difference between any two consecutive elements is the same.

For example, [1, 3, 5, 7, 9], [7, 7, 7, 7], and [3, -1, -5, -9] are arithmetic sequences.
For example, [1, 1, 2, 5, 7] is not an arithmetic sequence.
A subsequence of an array is a sequence that can be formed
by removing some elements (possibly none) of the array.

For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
The test cases are generated so that the answer fits in 32-bit integer.

https://leetcode.com/problems/arithmetic-slices-ii-subsequence
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArithmeticSliceSubsequence {

    public static void main(String[] args) {
        ArithmeticSliceSubsequence solve = new ArithmeticSliceSubsequence();
        int[] nums = new int[]{7, 7, 7, 7, 7};
        System.out.println(solve.numberOfArithmeticSlices(nums));
    }

    /**
     * Solves using DP.
     * dp[i][d] denotes the number of subsequences ending at i with difference d.
     * dp[i][d] is initialized for each i by looking at possible 3-component subsequences
     * dp[i][d] += sum{dp[j][d]} for all those nums[j] + d == nums[i], j < i
     * @param nums the array
     * @return the number of subsequences
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return 0;
        }
        int ans = 0;
        List<Map<Long, Integer>> dp = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            dp.add(new HashMap<>());
        }
        for (int i = 2; i < n; i++) {
            // initialize
            Map<Long, Integer> currMap = dp.get(i);
            for (int j = 0; j < i - 1; j++) {
                for (int k = j + 1; k < i; k++) {
                    if ((long) nums[k] - nums[j] == (long) nums[i] - nums[k]) {
                        long diff = nums[k] - nums[j];
                        currMap.putIfAbsent(diff, 0);
                        currMap.put(diff, currMap.get(diff) + 1);
                    }
                }
            }
            // dp
            for (int j = 2; j < i; j++) {
                Map<Long, Integer> prevMap = dp.get(j);
                for (long diff: prevMap.keySet()) {
                    if (nums[j] + diff == nums[i]) {
                        currMap.putIfAbsent(diff, 0);
                        currMap.put(diff, currMap.get(diff) + prevMap.get(diff));
                    }
                }
            }
            // evaluate
            for (int count: currMap.values()) {
                ans += count;
            }
        }

        return ans;
    }
}
