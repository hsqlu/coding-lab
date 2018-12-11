package algorithm.leetcode.problem.P257;

import algorithm.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 257. Binary Tree Paths
 * Easy
 * 653
 * 51
 *
 *
 * Given a binary tree, return all root-to-leaf paths.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Input:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * Output: ["1->2->5", "1->3"]
 *
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 *
 */
public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        if (root.left == null && root.right == null) {
            result.add("" + root.val);
        } else {
            for (String a : binaryTreePaths(root.left)) {
                result.add(root.val + "->" + a);
            }
            for (String b : binaryTreePaths(root.right)) {
                result.add(root.val + "->" + b);
            }
        }

        return result;
    }
}
