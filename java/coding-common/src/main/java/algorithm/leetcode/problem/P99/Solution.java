package algorithm.leetcode.problem.P99;

import algorithm.common.ListNode;
import algorithm.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.BiFunction;

/**
 * 99. Recover Binary Search Tree
 * Hard
 *
 * 581
 *
 * 39
 *
 * Favorite
 *
 * Share
 * Two elements of a binary search tree (BST) are swapped by mistake.
 *
 * Recover the tree without changing its structure.
 *
 * Example 1:
 *
 * Input: [1,3,null,null,2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * Output: [3,1,null,null,2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 * Example 2:
 *
 * Input: [3,1,4,null,null,2]
 *
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 *
 * Output: [2,1,4,null,null,3]
 *
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 * Follow up:
 *
 * A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution?
 */
public class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return mustGreater(root.val, root.right) && mustLess(root.val, root.left) && isValidBST(root.left) && isValidBST(root.right);
    }
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }

//        TreeNode current = root;
//        Stack<TreeNode> temp = new Stack<>();
//        while (current != null) {
//            temp.push(current);
//            current = current.left;
//        }
//        current = temp.pop();
//        current.right


        search(root.val, root.right, (target, val) -> target < val);
        search(root.val, root.left, (target, val) -> target > val);


    }

    public int[] nextLargerNodes(ListNode head) {
        if (head == null)
            return null;
        List<Integer> result = new ArrayList();
        int first = head.val;
        ListNode point = head;

        while(point != null) {
            int cur = point.val;
            int nextLarger = 0;
            ListNode temp = point;
            while (temp.next != null) {
                if (temp.next.val >= cur) {
                    nextLarger = temp.next.val;
                    break;
                }
                temp = temp.next;
            }
            result.add(nextLarger);
            point = point.next;
        }
        return result.stream().mapToInt(i->i).toArray();
    }

    private int search(int val, TreeNode right, BiFunction<Integer, Integer, Boolean> compare) {
        int i = 0;
        if (right == null) {
            return i;
        }

        if (compare.apply(val, right.val)) {

        }

        return i;
    }

    private boolean mustGreater(int val, TreeNode right) {
        if (right != null) {
            return right.val >= val && mustGreater(val, right.left) && mustGreater(val, right.right);
        }
        return true;
    }

    private boolean mustLess(int val, TreeNode left) {
        if (left != null) {
            return left.val <= val && mustLess(val, left.left) && mustLess(val, left.right);
        }
        return true;
    }

    private boolean mustBinarySearchTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        return mustBinarySearchTree(root.left) && mustBinarySearchTree(root.right);
    }
}
