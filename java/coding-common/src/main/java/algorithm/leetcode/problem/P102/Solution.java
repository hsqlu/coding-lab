package algorithm.leetcode.problem.P102;

import algorithm.common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. Binary Tree Level Order Traversal
 * Medium
 * <p>
 * <p>
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its level order traversal as:
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root != null)
            queue.push(root);
        while (!queue.isEmpty()) {
            List<TreeNode> nodes = process(queue);
            List<Integer> data = new LinkedList<>();
            for (TreeNode node : nodes) {
                data.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            result.add(data);
        }
        return result;
    }

    public List<TreeNode> process(Queue<TreeNode> queue) {
        List<TreeNode> nodes = new LinkedList<>();
        while (!queue.isEmpty()) {
            nodes.add(queue.poll());
        }
        return nodes;
    }
}
