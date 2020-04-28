package algorithm.leetcode.problem.P543;

import algorithm.common.TreeNode;

/**
 * 543. Diameter of Binary Tree
 * Easy
 * <p>
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 * <p>
 * Example:
 * Given a binary tree
 * <p>
 *       1
 *     / \
 *    2   3
 *  / \
 * 4   5
 * <p>
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * <p>
 * Note: The length of path between two nodes is represented by the number of edges between them.
 */
public class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int result = Math.max(diameterOfBinaryTree(root.left), diameterOfBinaryTree(root.right));

        return Math.max(result, highth(root.left) + highth(root.right));
    }

    int highth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(highth(root.left), highth(root.right));

    }
}
