package algorithm.microsoft.list;

import algorithm.common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Merge k Sorted Lists
 * Solution
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 *
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        for (ListNode node : lists) {
            ListNode head = node;
            while (head != null) {
                pq.add(head);
                head = head.next;
            }
        }
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        while (!pq.isEmpty()) {
            head.next = pq.remove();
            head = head.next;
        }
        head.next = null;
        return dummy.next;
    }

    public ListNode merge(ListNode[] lists) {
        int n = lists.length;

        int interval = 1;

        while (interval < n) {
            for (int i = 0; i < n - interval; interval = interval*2) {
                lists[i] = merge(lists[i], lists[i+interval]);
            }
            interval *= 2;
        }
        return n > 0 ? lists[0] : null;
    }

    private ListNode merge(ListNode list, ListNode list1) {
        return null;
    }
}
