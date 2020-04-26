package algorithm.leetcode.problem.P863;

import algorithm.common.TreeNode;

import java.util.*;

/**
 * 863. All Nodes Distance K in Binary Tree
 * Medium
 *
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 *
 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 *
 * Output: [7,4,1]
 *
 * Explanation:
 * The nodes that are a distance 2 from the target node (with value 5)
 * have values 7, 4, and 1.
 *
 *
 *
 * Note that the inputs "root" and "target" are actually TreeNodes.
 * The descriptions of the inputs above are just serializations of these objects.
 *
 *
 *
 * Note:
 *
 *     The given tree is non-empty.
 *     Each node in the tree has unique values 0 <= node.val <= 500.
 *     The target node is a node in the tree.
 *     0 <= K <= 1000.
 */
public class Solution {
    private int dis(TreeNode root, TreeNode target, int K, List<Integer> result) {
        if (null == root) {
            return -1;
        }
        if (root == target) {
            collect(target, K, result);
            return 0;
        }
        int l = dis(root.left, target, K, result);
        int r = dis(root.right, target, K, result);

        if (l >= 0) {
            if (l == K - 1)
                result.add(root.val);
            collect(root.right, K - l - 2, result);
            return l + 1;
        }

        if (r >= 0) {
            if (r == K - 1)
                result.add(root.val);
            collect(root.left, K - r - 2, result);
            return r + 1;
        }
        return -1;
    }

    void collect(TreeNode root, int d, List<Integer> result) {
        if (root == null || d < 0)
            return;
        if (d == 0)
            result.add(root.val);
        collect(root.left, d - 1, result);
        collect(root.right, d - 1, result);
    }

    public List<Integer> secondDistanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        dis(root, target, K, result);
        return result;
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new ArrayList<>();
        if (null == root) {
            return result;
        }

        HashMap<TreeNode, List<TreeNode>> graph = new HashMap<>();
        buildGraph(root, graph);
        HashSet<TreeNode> visited = new HashSet<>();
        visited.add(target);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(target);


        int k = 0;
        while (!q.isEmpty() && k <= K) {
            int s = q.size();
            while (s > 0) {
                TreeNode node = q.remove();
                s--;
                if (k == K) {
                    result.add(node.val);
                }
                for (TreeNode n : graph.getOrDefault(node, new ArrayList<>())) {
                    if (visited.contains(n)) {
                        continue;
                    }
                    visited.add(n);
                    q.offer(n);
                }
            }
            k++;
        }
        return result;

    }

    private void buildGraph(TreeNode root, HashMap<TreeNode, List<TreeNode>> g) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            List<TreeNode> leaves = g.getOrDefault(root, new ArrayList<>());
            leaves.add(root.left);
            g.put(root, leaves);
            List<TreeNode> nodes = g.getOrDefault(root.left, new ArrayList<>());
            nodes.add(root);
            g.put(root.left, nodes);
        }

        if (root.right != null) {
            List<TreeNode> leaves = g.getOrDefault(root, new ArrayList<>());
            leaves.add(root.right);
            g.put(root, leaves);
            List<TreeNode> nodes = g.getOrDefault(root.right, new ArrayList<>());
            nodes.add(root);
            g.put(root.right, nodes);
        }

        buildGraph(root.left, g);
        buildGraph(root.right, g);
    }
}
