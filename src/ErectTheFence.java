/*
You are given an array trees where trees[i] = [xi, yi]
represents the location of a tree in the garden.
You are asked to fence the entire garden using the minimum length
of rope as it is expensive.
 The garden is well fenced only if all the trees are enclosed.

Return the coordinates of trees that are exactly located on the fence perimeter.

https://leetcode.com/problems/erect-the-fence/
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ErectTheFence {

    public static void main(String[] args) {
        ErectTheFence solve = new ErectTheFence();
        int[][] trees = new int[][]{{0, 0}, {1, -5}, {5, -5}, {4, -3}, {3, -2}, {5, 0}};
        int[][] result = solve.outerTreesJarvis(trees);
        System.out.println(Arrays.deepToString(result));
    }

    public static double orientation(int[] start, int[] end) {
        return Math.atan2(end[1] - start[1], end[0] - start[0]);
    }

    public static boolean isConvex(double angle1, double angle2) {
        if (angle1 >= 0) {
            double angle1reverse = angle1 - Math.PI;
            return angle2 >= angle1 || angle2 <= angle1reverse;
        } else {
            double angle1reverse = angle1 + Math.PI;
            return angle1 <= angle2 && angle2 <= angle1reverse;
        }
    }

    public static double distance(int[] start, int[] end) {
        return Math.pow(start[0] - end[0], 2) + Math.pow(start[1] - end[1], 2);
    }

    /**
     * Uses Graham method to find the outer edge.
     * Starts with the left-most tree, sort the remaining trees according to their angle.
     * Go counter-clockwise, eliminate any concave outer edge.
     * @param trees the input trees
     * @return the trees on the edge
     */
    public int[][] outerTrees(int[][] trees) {
        // find the left-most, bottom-most tree
        int[] begin = trees[0];
        for (int[] tree : trees) {
            if (tree[0] < begin[0] || (tree[0] == begin[0] && tree[1] < begin[1])) {
                begin = tree;
            }
        }
        // sort
        List<int[]> listTrees = new ArrayList<>();
        for (int[] tree : trees) {
            if (tree != begin) {
                listTrees.add(tree);
            }
        }
        int[] finalBegin = begin;
        listTrees.sort((tree1, tree2) -> {
            int comp = Double.compare(orientation(finalBegin, tree1), orientation(finalBegin, tree2));
            if (comp != 0) {
                return comp;
            } else {
                return -Double.compare(distance(finalBegin, tree1), distance(finalBegin, tree2));
            }
        });

        // Graham Scan
        List<int[]> stack = new ArrayList<>();
        stack.add(begin);
        stack.add(listTrees.get(0));
        int i = 1;
        while (i < listTrees.size() &&
                orientation(begin, listTrees.get(i)) == orientation(begin, listTrees.get(0))) {
            stack.add(1, listTrees.get(i));
            i++;
        }
        for (; i < listTrees.size(); i++) {
            stack.add(listTrees.get(i));
            while (stack.size() > 2) {
                int[] p0 = stack.get(stack.size() - 3);
                int[] p1 = stack.get(stack.size() - 2);
                int[] p2 = stack.get(stack.size() - 1);
                if (!isConvex(orientation(p1, p2), orientation(p1, p0))) {
                    stack.remove(stack.size() - 2);
                } else {
                    break;
                }
            }
        }

        int[][] result = new int[stack.size()][2];
        for (i = 0; i < stack.size(); i++) {
            result[i] = stack.get(i);
        }
        return result;
    }

    /**
     * Uses Jarvis method to find the outer edge.
     * Start from the left most node. Find the next node by finding the edge that
     * makes a convex vertex with the largest angle.
     * @param trees input trees
     * @return trees on the edge
     */
    public int[][] outerTreesJarvis(int[][] trees) {
        // find the left-most, bottom-most tree
        int[] begin = trees[0];
        for (int[] tree : trees) {
            if (tree[0] < begin[0] || (tree[0] == begin[0] && tree[1] < begin[1])) {
                begin = tree;
            }
        }

        // Use jarvis method
        List<int[]> result = new ArrayList<>();
        result.add(begin);
        int[] curr = begin == trees[0] ? trees[1] : trees[0];
        for (int[] tree: trees) {
            if (tree == begin) {
                continue;
            }
            double currOrientation = orientation(begin, curr);
            double nextOrientation = orientation(begin, tree);
            if ((currOrientation > nextOrientation) ||
                    (currOrientation == nextOrientation && distance(begin, curr) > distance(begin, tree)))  {
                curr = tree;
            }
        }
        result.add(curr);
        while (curr != begin) {
            int[] next = begin;
            double angle1 = orientation(result.get(result.size() - 1), result.get(result.size() - 2));
            for (int[] tree: trees) {
                if (tree == curr) {
                    continue;
                }
                double nextAngle2 = orientation(result.get(result.size() - 1), next);
                double treeAngle2 = orientation(result.get(result.size() - 1), tree);
                if (getAngleDiff(angle1, nextAngle2) < getAngleDiff(angle1, treeAngle2)) {
                    next = tree;
                } else if (getAngleDiff(angle1, nextAngle2) == getAngleDiff(angle1, treeAngle2)
                        && distance(result.get(result.size() - 1), next) > distance(result.get(result.size() - 1), tree)) {
                    next = tree;
                }
            }
            result.add(next);
            curr = next;
        }

        result.remove(0);

        int[][] returnVal = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            returnVal[i] = result.get(i);
        }
        return returnVal;
    }

    private double getAngleDiff(double angle1, double angle2) {
        if (angle1 <= 0) {
            if (angle2 <= 0) {
                return angle1 - angle2;
            } else {
                return angle1 - angle2 + 2 * Math.PI;
            }
        } else {
            return angle1 - angle2;
        }
    }


}
