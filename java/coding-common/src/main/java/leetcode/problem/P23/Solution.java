package leetcode.problem.P23;

import leetcode.problem.ListNode;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.PriorityQueue;

/**
 * 23. Merge k Sorted Lists
 * Hard
 * 1761
 * 114
 * <p>
 * <p>
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length <= 0) {
            return null;
        }

        if (lists.length > 2) {
            int count = lists.length / 2;
            ListNode[] A = new ListNode[count];
            ListNode[] B = new ListNode[lists.length - count];
            for (int i = 0; i < lists.length; i++) {
                if (i < count) {
                    A[i] = lists[i];
                } else {
                    B[i - count] = lists[i];
                }
            }
            return mergeLists(mergeKLists(A), mergeKLists(B));
        } else if (lists.length == 2) {
            return mergeLists(lists[0], lists[1]);
        } else {
            return lists[0];
        }
    }

    public ListNode mergeLists(ListNode A, ListNode B) {
        ListNode dump = new ListNode(-1);
        ListNode head = dump;
        while (A != null && B != null) {
            if (A.val < B.val) {
                head.next = new ListNode(A.val);
                A = A.next;
            } else {
                head.next = new ListNode(B.val);
                B = B.next;
            }
            head = head.next;
        }
        while (A != null) {
            head.next = new ListNode(A.val);
            A = A.next;
            head = head.next;
        }
        while (B != null) {
            head.next = new ListNode(B.val);
            B = B.next;
            head = head.next;
        }
        return dump.next;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (ListNode node : lists) {
            while (node != null) {
                queue.offer(node.val);
                node = node.next;
            }
        }
        return getNode(queue);
    }

    private ListNode getNode(PriorityQueue<Integer> queue) {
        if (!queue.isEmpty()) {
            ListNode node = new ListNode(queue.poll());
            node.next = getNode(queue);
            return node;
        }
        return null;
    }
}
