package algorithm.leetcode.problem.P107;

import algorithm.common.TreeNode;

import java.util.*;

/**
 * 107. Binary Tree Level Order Traversal II
 * Easy
 *
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * return its bottom-up level order traversal as:
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (null == root) {
            return result;
        }

        Deque<List<Integer>> stack = traversal(root);
        while (!stack.isEmpty()) {
            result.add(stack.pollLast());
        }
        return result;
    }

    Deque<List<Integer>> traversal(TreeNode root) {
        Deque<List<Integer>> stack = new LinkedList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (queue.size() > 0) {
            List<Integer> current = new ArrayList<>();
            Queue<TreeNode> sub = new LinkedList<>();

            while (!queue.isEmpty()) {
                TreeNode node = queue.remove();
                current.add(node.val);
                if (node.left != null) {
                    sub.offer(node.left);
                }
                if (node.right != null) {
                    sub.offer(node.right);
                }
            }
            stack.offerLast(current);
            queue = sub;
        }
        return stack;
    }

}
