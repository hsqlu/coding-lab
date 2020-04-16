package algorithm.microsoft;

import algorithm.common.TreeNode;

/**
 * In a binary tree, if in the path from root to the node A, there is no node with greater value than A’s, this node A is visible.
 * We need to count the number of visible nodes in a binary tree.
 *
 * Example 1:
 *
 * Input:
 *         5
 *      /     \
 *    3        10
 *   /  \     /
 * 20   21   1
 *
 * Output: 4
 * Explanation: There are 4 visible nodes: 5, 20, 21, and 10.
 *
 * Example 2:
 *
 * Input:
 *   -10
 * 	\
 * 	-15
 * 	   \
 * 	   -1
 *
 * Output: 2
 * Explanation: Visible nodes are -10 and -1.
 */
public class CountVisibleNodes {

    public int visibleNode(TreeNode root) {

        return count(root, Integer.MIN_VALUE);
    }

    private int count(TreeNode root, int val) {
        int sum = 0;
        if (root == null) {
            return sum;
        }


       if (root.val < val) {
            return count(root.left, val) + count(root.right, val);
        } else {
            return count(root.left, root.val) + count(root.right, root.val) + 1;
        }

    }
}
