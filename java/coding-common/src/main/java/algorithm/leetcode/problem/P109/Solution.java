package algorithm.leetcode.problem.P109;

import algorithm.common.ListNode;
import algorithm.common.TreeNode;

/**
 * 109. Convert Sorted List to Binary Search Tree
 * Medium
 *
 * 895
 *
 * 63
 *
 * Favorite
 *
 * Share
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example:
 *
 * Given the sorted linked list: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class Solution {

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null) {
            return new TreeNode(head.val);
        }

        ListNode fast = head.next;
        ListNode slow = null;
        while (fast != null && fast.next != null) {
            if (slow == null) {
                slow = head;
            } else {
                slow = slow.next;
            }
            fast = fast.next.next;
        }
        if (slow == null) {
            slow = head;
        }
        TreeNode root = new TreeNode(slow.next.val);
        root.right = sortedListToBST(slow.next.next);
        slow.next = null;
        root.left = sortedListToBST(head);
        return root;
    }
}
