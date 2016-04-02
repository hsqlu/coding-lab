package com.lingtoo.common.setting;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created: 2015/12/25.
 * Author: Qiannan Lu
 */
public class Solution {

    @Test
    public void convertToTitle() {
        compareVersion("1.0", "1.0.1");
    }

    public int compareVersion(String version1, String version2) {
        if (version1.equals(version2))
            return 0;
;        if (version1.isEmpty()) {
            if (Integer.parseInt(version1) == 0)
                return 0;
            return -1;
        }
        if (version2.isEmpty()) {
            if (Integer.parseInt(version2) == 0)
                return 0;
            return 1;
        }
        int v1, v2;
        if (!version1.contains(".")) {
            v1 = Integer.parseInt(version1);
            version1 = "";
        } else {
            v1 = Integer.parseInt(version1.substring(0, version1.indexOf(".")));
            version1 = version1.substring(version1.indexOf(".") + 1);
        }
        if (!version2.contains(".")) {
            v2 = Integer.parseInt(version2);
            version2 = "";
        } else {
            v2 = Integer.parseInt(version2.substring(0, version2.indexOf(".")));
            version2 = version2.substring(version2.indexOf(".") + 1);
        }

        return v1 > v2 ? 1 : (v1 < v2 ? -1 : compareVersion(version1, version2));
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val)
            return lowestCommonAncestor(root.right, p, q);
        if (root.val > p.val && root.val > q.val)
            return lowestCommonAncestor(root.left, p, q);
        return root;
    }

    @Test
    public void hammingWeight() {
        long n = 2147483648l;
        int sum = 0;
        while (n > 0) {
            sum = sum + (n % 2 == 0 ? 0 : 1);
            n /= 2;
        }
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode last = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = last;
            last = head;
            head = tmp;
        }
        return last;

    }

    /**
     * JLS 15.19 The operators << (left shift), >> (signed right shift), and >>> (unsigned right shift) are called the shift operators. [...]
     */
    @Test
    public void demo() {
        int n1 = 0b10000000000000000000000000000000; // = 2147483648 = 0x80000000
        int n2 = n1;
        System.out.printf("%-2s %-32s %-11s %-32s %-11s %-8s %-8s\n",
                "it", ">> binary", ">> decimal", ">>> binary", ">>> decimal", ">> hexa", ">>> hexa");
        for (int i = 0; i < 34; ++i) {
            System.out.printf("%2d %32s %11d %32s %11s %08x %08x\n",
                    i,
                    Integer.toBinaryString(n1), n1,
                    Integer.toBinaryString(n2), Integer.toUnsignedString(n2),
                    n1, n2
            );
            n1 = n1 >> 1;
            n2 = n2 >>> 1;
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
class MyQueue {
    static MyStack stack = new MyStack();
    // Push element x to the back of queue.
    public void push(int x) {
        stack.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        if (stack.size() == 1) {
            stack.peek();
            return;
        }

        MyStack s = new MyStack();
        while (!stack.isEmpty()) {
            s.push(stack.peek());
        }
        if (!s.isEmpty())
            s.peek();
        while(!s.isEmpty())
            stack.push(s.peek());
    }

    // Get the front element.
    public int peek() {
        if (empty()) {
            throw new IndexOutOfBoundsException();
        }
        MyStack s = new MyStack();
        while (!stack.isEmpty()) {
            s.push(stack.peek());
        }
        int front = s.peek();
        stack.push(front);
        while(!s.isEmpty())
            stack.push(s.peek());
        return front;
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stack.size() == 0;
    }
}
class MyStack {
    int size = 0;
    Integer[] stack = new Integer[100];

    public void push(int s) {
        stack[size] = s;
        size++;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        size--;
        int tmp = stack[size];
        stack[size] = null;
        return tmp;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}