/*
There is an undirected connected tree with n nodes
labeled from 0 to n - 1 and n - 1 edges.

You are given the integer n and the array edges
where edges[i] = [ai, bi] indicates that
there is an edge between nodes ai and bi in the tree.

Return an array answer of length n where answer[i] is
the sum of the distances between the ith node in the tree
and all other nodes.

https://leetcode.com/problems/sum-of-distances-in-tree/
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SumOfDistancesInTree {
    public static void main(String[] args) {
        SumOfDistancesInTree solve = new SumOfDistancesInTree();
        int n = 3;
        int[][] edges = new int[][]{{2, 0}, {1, 0}};
        System.out.println(Arrays.toString(solve.sumOfDistancesInTree(n, edges)));
    }

    /**
     * Given an edge connecting xy, we have:
     * distance_sum[x] = subtree_distance_sum[x] + subtree_distance_sum[y] + subtree_node_count[y]
     * distance_sum[y] = subtree_distance_sum[y] + subtree_distance_sum[x] + subtree_node_count[x]
     * Together we have:
     * distance_sum[x] = distance_sum[y] + subtree_node_count[y] - subtree_node_count[x]
     *
     * stsum: subtree_distance_sum
     * stnodes: subtree_node_count
     * We take node 0 as root, do post order traversal to find stsum and stnodes for each child
     * Now stsum[0] = distance_sum[0].
     * Then we do pre order traversal to use the equation above to find the sum
     * @param n     the number of nodes
     * @param edges the edges
     * @return the sum of distance from root x
     */
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        // build tree
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }
        for (int[] edge: edges) {
            nodes[edge[0]].children.add(nodes[edge[1]]);
            nodes[edge[1]].children.add(nodes[edge[0]]);
        }

        Node root = nodes[0];
        // get subtree sum distance and subtree node count
        int[] stsum = new int[n];
        int[] stnodes = new int[n];
        getSubtree(root, null, stsum, stnodes);

        // get result
        int[] result = new int[n];
        result[root.idx] = stsum[root.idx];
        calculateResult(root, null, n, stnodes, result);

        return result;
    }

    // post-order traversal
    private void getSubtree(Node node, Node parent, int[] stsum, int[] stnodes) {
        if (node == null) {
            return;
        }
        int currSum = 0;
        int currNodes = 0;
        for (Node child: node.children) {
            if (child == parent) {
                continue;
            }
            getSubtree(child, node, stsum, stnodes);
            currSum += stsum[child.idx];
            currNodes += stnodes[child.idx];
        }
        stsum[node.idx] = currNodes + currSum;
        stnodes[node.idx] = currNodes + 1;
    }

    // pre-order traversal
    private void calculateResult(Node node, Node parent, int n, int[] stnodes, int[] result) {
        for (Node child: node.children) {
            if (child == parent) {
                continue;
            }
            result[child.idx] = result[node.idx] + (n - stnodes[child.idx]) - stnodes[child.idx];
            calculateResult(child, node, n, stnodes, result);
        }
    }

    static class Node {
        int idx;
        List<Node> children = new ArrayList<>();
        // Node parent;

        public Node(int idx) {
            this.idx = idx;
        }
    }
}
