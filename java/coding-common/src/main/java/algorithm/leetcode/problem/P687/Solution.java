package algorithm.leetcode.problem.P687;

import algorithm.common.TreeNode;

/**
 * 687. Longest Univalue Path
 * Easy
 *
 * Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.
 *
 * The length of path between two nodes is represented by the number of edges between them.
 *
 *
 *
 * Example 1:
 *
 * Input:
 *
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           1   1   5
 *
 * Output: 2
 *
 *
 *
 * Example 2:
 *
 * Input:
 *
 *               1
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 *
 * Output: 2
 *
 *
 *
 * Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
 */
public class Solution {
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int l = longthWithVal(root.left, root.val) + longthWithVal(root.right, root.val);
        int max = Math.max(longestUnivaluePath(root.left), longestUnivaluePath(root.right));
        return Math.max(l, max);
    }


    public int longthWithVal(TreeNode root, int val) {
        if (root == null) {
            return 0;
        }

        if (root.val == val) {
            return 1 + Math.max(longthWithVal(root.left, val), longthWithVal(root.right, val));
        } else {
            return 0;
        }
    }
}
