package algorithm.pattern.list;

import algorithm.common.ListNode;

import java.util.HashSet;

public class LinkedListCycleII {

    public ListNode detectCycle(ListNode head) {
        ListNode none = new ListNode(-1);
        if (head == null || head.next == null) {
            return null;
        }
        HashSet<ListNode> cycle = new HashSet<>();
        ListNode slow = head;
        while (slow != null) {
            if (cycle.contains(slow)) {
                return slow;
            }
            cycle.add(slow);
            slow = slow.next;
        }

        return null;
    }
}
