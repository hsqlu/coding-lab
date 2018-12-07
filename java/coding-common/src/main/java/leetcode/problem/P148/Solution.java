package leetcode.problem.P148;

import leetcode.problem.ListNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 148. Sort List
 * Medium
 * 1008
 * 55
 * <p>
 * <p>
 * Sort a linked list in O(n log n) time using constant space complexity.
 * <p>
 * Example 1:
 * <p>
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 * <p>
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 */
public class Solution {
    public ListNode sortList(ListNode head) {
        List<Integer> result = new LinkedList<>();
        ListNode root = new ListNode(0);
        while (head != null) {
            result.add(head.val);
            head = head.next;
        }
        Integer[] array = result.toArray(new Integer[result.size()]);
        Arrays.sort(array);
        ListNode cur = root;
        for (int i = 0; i < array.length; i++) {
            cur.next = new ListNode(array[i]);
            cur = cur.next;
        }
        return root.next;
    }

    public ListNode mergeSrtList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode fast = head;
        ListNode slow = head;

        ListNode pre = null;

        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;

        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode moving = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                moving.next = l1;
                l1 = l1.next;
            } else {
                moving.next = l2;
                l2 = l2.next;
            }
            moving = moving.next;
        }
        if (l1 != null) moving.next = l1;
        if (l2 != null) moving.next = l2;

        return dummy.next;
    }
}
