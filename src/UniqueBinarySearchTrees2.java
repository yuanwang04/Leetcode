/*
Given an integer n, return all the structurally unique BST's (binary search trees),
which has exactly n nodes of unique values from 1 to n.

Return the answer in any order.

https://leetcode.com/problems/unique-binary-search-trees-ii/
 */

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTrees2 {

    public static void main(String[] args) {
        UniqueBinarySearchTrees2 solve = new UniqueBinarySearchTrees2();
        List<TreeNode> result = solve.generateTrees(3);
        System.out.println(result.size());
    }

    public List<TreeNode> generateTrees(int n) {
        return generateTreesRange(1, n);
    }

    /**
     * Recursively build the tree by building
     * the left, the right, and concatenating them together.
     *
     * @param l lower limit
     * @param h higher limit
     * @return the list of roots
     */
    private List<TreeNode> generateTreesRange(int l, int h) {
        List<TreeNode> result = new ArrayList<>();
        if (l == h) {
            result.add(new TreeNode(l));
            return result;
        }

        // left is null
        List<TreeNode> subResult = generateTreesRange(l + 1, h);
        for (TreeNode node : subResult) {
            TreeNode root = new TreeNode(l, null, node);
            result.add(root);
        }
        // left & right are not null
        for (int i = l + 1; i < h; i++) {
            List<TreeNode> leftSubResult = generateTreesRange(l, i - 1);
            List<TreeNode> rightSubResult = generateTreesRange(i + 1, h);

            for (TreeNode left : leftSubResult) {
                for (TreeNode right : rightSubResult) {
                    TreeNode root = new TreeNode(i, left, right);
                    result.add(root);
                }
            }
        }
        // right is null
        subResult = generateTreesRange(l, h - 1);
        for (TreeNode node : subResult) {
            TreeNode root = new TreeNode(h, node, null);
            result.add(root);
        }

        return result;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
