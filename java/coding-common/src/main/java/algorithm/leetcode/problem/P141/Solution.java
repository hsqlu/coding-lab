package algorithm.leetcode.problem.P141;

import algorithm.leetcode.problem.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 141. Linked List Cycle
 * Easy
 * 1165
 * 84
 *
 *
 * Given a linked list, determine if it has a cycle in it.
 *
 * Follow up:
 * Can you solve it without using extra space?
 */
public class Solution {


    public boolean hasCycle(ListNode head) {
        Set<ListNode> result = new HashSet<>();
        if (head == null) {
            return false;
        }
        while (head != null) {
            if (result.add(head)) {
                head = head.next;
            } else  {
                return true;
            }
        }
        return false;
    }

    private boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
