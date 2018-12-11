package algorithm.leetcode.problem.P445;

import algorithm.common.ListNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class SolutionTest {

    @Test
    void reverseCorrectly() {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);

        a.next = b;
        b.next = c;

        System.out.println(a);

        new Solution().reverse(a);

        List<Integer> result = new LinkedList<>();
        ListNode head = new ListNode(0);
        while (head != null) {
            result.add(head.val);
        }
        Integer[] array = result.toArray(new Integer[result.size()]);
        Arrays.sort(array);
        for (int i = array.length - 1; i >= 0; i--) {
            head.next = new ListNode(array[i]);
            head = head.next;
        }
    }
}