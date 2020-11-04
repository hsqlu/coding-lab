package algorithm.pattern.list;

import algorithm.common.ListNode;

public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;

        ListNode headOdd = odd;
        ListNode headEven = even;

        while (even != null) {
            odd.next = even.next;
            if (odd.next == null) {
                break;
            }
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = headEven;
        return headOdd;
    }
}
