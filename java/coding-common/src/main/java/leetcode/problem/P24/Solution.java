package leetcode.problem.P24;

import leetcode.problem.ListNode;

import java.util.List;

/**
 * 24. Swap Nodes in Pairs
 * Medium
 * 793
 * 65
 *
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * Example:
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * Note:
 *
 * Your algorithm should use only constant extra space.
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 */
public class Solution {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode result = head.next;
        ListNode second = head.next;
        ListNode first = head;

        first.next = second.next;
        second.next = first;

        while (first.next != null) {
            if (first.next.next == null) {
                break;
            }
            ListNode a = first.next;
            ListNode b = first.next.next;

            ListNode temp = b.next;
            first.next = b;

            b.next = a;
            a.next = temp;

            first = a;
        }
        return result;
    }
}
