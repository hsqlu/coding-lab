package algorithm.microsoft.list;

import algorithm.common.ListNode;

public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }

    public ListNode recursive(ListNode head) {
        if (head == null || head.next == null) {    // Remember to check head.next is not null, otherwise the newHead would be null and causes NullPointException
            return head;
        }
        ListNode next = head.next;
        head.next = null;
        ListNode newHead = recursive(next);
        next.next = head;

        return newHead;
    }
}
