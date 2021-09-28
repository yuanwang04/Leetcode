/*
INCLUDES HOUSE ROBBER, HOUSE ROBBER II, HOUSE ROBBER III.
https://leetcode.com/problems/house-robber
https://leetcode.com/problems/house-robber-ii/
https://leetcode.com/problems/house-robber-iii/
 */

import java.util.*;

public class HouseRobber {

    public static void main(String[] args) {

    }

    /**
     * You are robbing a street of houses. You cannot rob two adjacent houses.
     * Return the maximum value you can rob.
     *
     * Uses DP to solve.
     * dp[i] means the maximum sum of nums[0:i]
     * dp[i] = max(dp[i-1], dp[i-2] + nums[i])
     * @param nums the array
     * @return the maximum sum
     */
    public int rob1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i < n + 1; i++) {
            dp[i] = Math.max(dp[i-1], nums[i-1] + dp[i-2]);
        }

        return dp[n];
    }

    /**
     * You are robbing a circular street of houses, which means
     * the first house is adjacent to the last house.
     * You cannot rob two adjacent houses.
     * Return the maximum value you can rob.
     *
     * Solves using DP.
     * First, try not rob the last house.
     * Then, try not rob the first house.
     * @param nums the values
     * @return the maximum of values robbed
     */
    public int rob2(int[] nums) {
        int n = nums.length;
        if (n <= 3) {
            int max = nums[0];
            for (int i = 1; i < n; i++) {
                max = Math.max(nums[i], max);
            }
            return max;
        }

        int[] dp1 = new int[n];  // robbing the first, ignoring the last
        int[] dp2 = new int[n];  // not robbing the first, ignoring it
        dp1[0] = 0;
        dp1[1] = nums[0];
        dp2[0] = 0;
        dp2[1] = nums[1];

        for (int i = 1; i < n-1; i++) {
            dp1[i+1] = Math.max(dp1[i], nums[i] + dp1[i-1]);
            dp2[i+1] = Math.max(dp2[i], nums[i+1] + dp2[i-1]);
        }

        return Math.max(dp1[n-1], dp2[n-1]);
    }



    static Map<TreeNode, Integer> cache = new HashMap<>();
    /**
     * You are robbing a tree of street. No two directly-connected houses
     * can be robbed. Return the maximum of possible value robbed.
     *
     * Uses recursive DFS + memorization to solve.
     * rob(node) = max(rob this node, not rob this node)
     *           = max(node.val + rob(node.left.left) + rob(node.right.right)
     *                 + rob(node.left.right) + rob(node.right.left),
     *                 rob(node.left) + rob(node.right))
     * @param root the root of the binary tree
     * @return the maximum number of value robbed
     */
    public int rob3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (cache.containsKey(root)) {
            return cache.get(root);
        }

        // not robbing this node
        int notRob = rob3(root.left) + rob3(root.right);

        // robbing this node
        int rob = 0;
        if (root.left != null) {
            rob += rob3(root.left.left) + rob3(root.left.right);
        }
        if (root.right != null) {
            rob += rob3(root.right.left) + rob3(root.right.right);
        }

        int ans = Math.max(rob, notRob);
        cache.put(root, ans);
        return ans;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


}
