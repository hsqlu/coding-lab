package algorithm.leetcode.problem.P445;

import algorithm.common.ListNode;

/**
 * 445. Add Two Numbers II
 * Medium
 * 539
 * 77
 * <p>
 * <p>
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 * <p>
 * Example:
 * <p>
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */
public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newL1 = reverse(l1);
        ListNode newL2 = reverse(l2);

        ListNode result = new ListNode(-1);
        int carry = 0;
        while (newL1 != null && newL2 != null) {
            int sum = newL1.val + newL2.val + carry;
            carry = sum / 10;
            ListNode newNode = new ListNode(sum % 10);
            newNode.next = result.next;
            result.next = newNode;
            newL1 = newL1.next;
            newL2 = newL2.next;
        }

        while (newL1 != null) {
            int sum = newL1.val + carry;
            carry = sum / 10;
            ListNode newNode = new ListNode(sum % 10);
            newNode.next = result.next;
            result.next = newNode;
            newL1 = newL1.next;
        }

        while (newL2 != null) {
            int sum = newL2.val + carry;
            carry = sum / 10;
            ListNode newNode = new ListNode(sum % 10);
            newNode.next = result.next;
            result.next = newNode;
            newL2 = newL2.next;
        }

        if (carry > 0) {
            ListNode newNode = new ListNode(carry);
            newNode.next = result.next;
            result.next = newNode;
        }

        return result.next;
    }

    public ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode tmp = head.next;
        head.next = null;
        while (tmp != null) {
            ListNode next = tmp.next;
            ListNode current = tmp;
            current.next = head;
            tmp = next;
            head = current;
        }

        return head;
    }
}
