/*
You are given an undirected graph (the "original graph") with n nodes
labeled from 0 to n - 1. You decide to subdivide each edge in the graph
into a chain of nodes, with the number of new nodes varying between each edge.

The graph is given as a 2D array of edges where edges[i] = [ui, vi, cnti]
indicates that there is an edge between nodes ui and vi in the original graph,
and cnti is the total number of new nodes that you will subdivide the edge into.
Note that cnti == 0 means you will not subdivide the edge.

To subdivide the edge [ui, vi], replace it with (cnti + 1) new edges
and cnti new nodes. The new nodes are x1, x2, ..., xcnti, and
the new edges are [ui, x1], [x1, x2], [x2, x3], ..., [xcnti-1, xcnti], [xcnti, vi].

In this new graph, you want to know how many nodes are reachable
from the node 0, where a node is reachable if the distance is maxMoves or less.

Given the original graph and maxMoves, return the number of nodes
that are reachable from node 0 in the new graph.

https://leetcode.com/problems/reachable-nodes-in-subdivided-graph
 */

import java.util.*;

public class ReachableNodesInSubdividedGraph {

    public static void main(String[] args) {
        ReachableNodesInSubdividedGraph solve = new ReachableNodesInSubdividedGraph();
        int[][] edges = new int[][]{
                {3,4,8},
                {0,1,3},
                {1,4,0},
                {1,2,3},
                {0,3,2},
                {0,4,10},
                {1,3,3},
                {2,4,3},
                {2,3,3},
                {0,2,10}
        };
        int maxMoves = 7;
        int n = 5;
        System.out.println(solve.reachableNodes(edges, maxMoves, n));
    }


    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        Map<Integer, Map<Integer, Integer>> edgeMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            edgeMap.put(i, new HashMap<>());
        }
        for (int[] edge : edges) {

                edgeMap.get(edge[0]).put(edge[1], edge[2]);
                edgeMap.get(edge[1]).put(edge[0], edge[2]);

        }

        Map<Integer, Integer> distMap = new HashMap<>();
        distMap.put(0, 0);
        Map<Integer, Integer> used = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<Node>((node1, node2) -> node1.dist - node2.dist);
        pq.add(new Node(0, 0));

        int sum = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.dist > maxMoves) {
                break;
            }
            if (used.containsKey(node.node)) {
                continue;
            }
            used.put(node.node, maxMoves - node.dist);

            sum++;  // for current node

            Map<Integer, Integer> outEdges = edgeMap.get(node.node);
            for (int nextNode: outEdges.keySet()) {
                int distFromCurr = node.dist + outEdges.get(nextNode) + 1;
                if (!distMap.containsKey(nextNode) || distFromCurr < distMap.get(nextNode)) {
                    distMap.put(nextNode, distFromCurr);
                    pq.add(new Node(nextNode, distFromCurr));
                }
                if (distFromCurr <= maxMoves) {
                    sum += Math.max(0, outEdges.get(nextNode) - used.getOrDefault(nextNode, 0));
                } else {
                    int stepLeft = maxMoves - node.dist;
                    sum += Math.max(0, Math.min(outEdges.get(nextNode) - used.getOrDefault(nextNode, 0), stepLeft));
                }
            }
        }

        return sum;
    }

    static class Node {
        int node;
        int dist;

        public Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }
}
