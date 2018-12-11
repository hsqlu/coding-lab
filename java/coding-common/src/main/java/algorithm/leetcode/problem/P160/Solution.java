package algorithm.leetcode.problem.P160;

import algorithm.leetcode.problem.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 160. Intersection of Two Linked Lists
 * Easy
 * 1630
 * 104
 *
 *
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 *
 * For example, the following two linked lists:
 *
 * A:          a1 → a2
 *                    ↘
 *                      c1 → c2 → c3
 *                    ↗
 * B:     b1 → b2 → b3
 * begin to intersect at node c1.
 *
 *
 * Notes:
 *
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 * Credits:
 * Special thanks to @stellari for adding this problem and creating all test cases.
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> tem = new HashSet<>();

        while (headA != null) {
            tem.add(headA);
            headA = headA.next;
        }
        while (headB != null && !tem.contains(headB)) {
            headB = headB.next;
        }

        return headB;
    }
}
