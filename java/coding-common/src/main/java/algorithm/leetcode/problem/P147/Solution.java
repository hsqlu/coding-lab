package algorithm.leetcode.problem.P147;

import algorithm.common.ListNode;

/**
 * 147. Insertion Sort List
 * Medium
 * 255
 * 314
 * Favorite
 * Share
 * Sort a linked list using insertion sort.
 * <p>
 * <p>
 * A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.
 * With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list
 * <p>
 * <p>
 * Algorithm of Insertion Sort:
 * <p>
 * Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
 * At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
 * It repeats until no input elements remain.
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
    public ListNode insertionSortList(ListNode head) {
        ListNode result = null;
        ListNode next = head;
        while (next != null) {
            ListNode current = next;
            next = next.next;
            current.next = null;
            result = insert(result, current);
        }
        return result;
    }

    private ListNode insert(ListNode result, ListNode next) {
        ListNode head = result;
        ListNode pre = null;
        if (head == null) {
            return next;
        }
        while (head != null) {

            if (head.val < next.val) {
                if (head.next == null) {
                    head.next = next;
                    break;
                } else {
                    pre = head;
                    head = head.next;
                }
            } else {
                if (pre != null) {
                    pre.next = next;
                    next.next = head;
                    break;
                } else {
                    next.next = head;
                    result = next;
                    break;
                }
            }

        }
        return result;
    }
}
