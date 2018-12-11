package algorithm.leetcode.problem.P203;

import algorithm.leetcode.problem.ListNode;

/**
 * 203. Remove Linked List Elements
 * Easy
 * 620
 * 38
 *
 *
 * Remove all elements from a linked list of integers that have value val.
 *
 * Example:
 *
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5
 */
public class Solution {
    public ListNode removeElements(ListNode head, int val) {

        while (head != null && head.val == val) {
            head = head.next;
        }

        ListNode pre = head;
        ListNode current = head;
        while (current != null) {
            if (current.val == val) {
                current = current.next;
                pre.next = current;
            } else {
                pre = current;
                current = current.next;
            }
        }

        return head;
    }
}
