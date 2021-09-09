/*
    You are given an integer array nums of length n where nums is a permutation
    of the numbers in the range [0, n - 1].

    You should build a set s[k] = {nums[k], nums[nums[k]], nums[nums[nums[k]]], ... }
    subjected to the following rule:

    The first element in s[k] starts with the selection of the element nums[k] of index = k.
    The next element in s[k] should be nums[nums[k]], and then nums[nums[nums[k]]], and so on.
    We stop adding right before a duplicate element occurs in s[k].

    Return the longest length of a set s[k].

    https://leetcode.com/problems/array-nesting/
 */

public class ArrayNesting {

    public static void main(String[] args) {
        ArrayNesting arrayNesting = new ArrayNesting();
        int[] nums = new int[]{5, 4, 0, 3, 1, 6, 2};
        System.out.println(arrayNesting.arrayNesting(nums));
    }

    /*
    This is a DFS problem.
    Each node refers to 1 node, and is referred by 1 node.
    That means EVERY NODE is in a circle, we just have to find the largest circle.
    Time complexity: O(N), each node visited once.
     */
    public int arrayNesting(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int k = i;
            int circleLen = 0;
            while (nums[k] != -1) {
                int prevK = k;
                k = nums[k];
                nums[prevK] = -1;
                circleLen++;
            }
            result = Math.max(result, circleLen);
        }
        return result;
    }
}
