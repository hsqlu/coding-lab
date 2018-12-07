package leetcode.problem.P92;

import leetcode.problem.CommonUtil;
import leetcode.problem.ListNode;

/**
 * 92. Reverse Linked List II
 * Medium
 * 832
 * 65
 *
 *
 * Reverse a linked list from position m to n. Do it in one-pass.
 *
 * Note: 1 ≤ m ≤ n ≤ length of list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 */
public class Solution {
    public static void main(String[] args) {
        new Solution().reverseBetween(CommonUtil.stringToListNode("[3, 5]"), 1, 2);
    }
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n) {
            return head;
        }
        ListNode start = head;
        ListNode end = head;
        for (int i = 1; i < n; i++) {
            if (i < m) {
                start = start.next;
                end = end.next;
            } else {
                end = end.next;
            }
        }
        ListNode rest = end.next;
        end.next = null;

        ListNode cur = head;
        if (m == 1) {
            head = reverse(start);
        } else {
            for (int i = 1; i < m - 1; i++) {
                cur = cur.next;
            }
            cur.next = reverse(start);
        }

        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = rest;
        return head;
    }

    private ListNode reverse(ListNode head) {
        ListNode current = head;
        if (head == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = null;
        while (next != null) {
            ListNode tmp = next.next;
            next.next = current;
            current = next;
            next = tmp;
        }

        return current;
    }
}
