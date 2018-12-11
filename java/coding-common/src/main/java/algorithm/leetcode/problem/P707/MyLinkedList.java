package algorithm.leetcode.problem.P707;

import java.util.ArrayList;
import java.util.List;

/**
 * 707. Design Linked List
 * Easy
 * 165
 * 49
 * <p>
 * <p>
 * Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list. A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node. If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.
 * <p>
 * Implement these functions in your linked list class:
 * <p>
 * get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
 * addAtHead(val) : Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
 * addAtTail(val) : Append a node of value val to the last element of the linked list.
 * addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
 * deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.
 * Example:
 * <p>
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
 * linkedList.get(1);            // returns 2
 * linkedList.deleteAtIndex(1);  // now the linked list is 1->3
 * linkedList.get(1);            // returns 3
 * Note:
 * <p>
 * All values will be in the range of [1, 1000].
 * The number of operations will be in the range of [1, 1000].
 * Please do not use the built-in LinkedList library.
 * Accepted
 * 12,007
 * Submissions
 * 60,873
 */
public class MyLinkedList {
    private Node header;
    private Node tail;
    private static int count;

    class Node {
        private int value;
        private Node previous;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        this.header = null;
        this.tail = null;
        this.count = 0;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || index >= count) {
            return -1;
        }
        Node result = header;
        for (int i = 0; i < index; i++) {
            if (result != null) {
                result = result.next;
            }
        }
        return result != null ? result.value : -1;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        Node temp = this.header;
        Node insert = new Node(val);
        if (temp == null) {
            this.tail = insert;
            this.header = insert;
        } else {
            insert.next = temp.next;
            temp.previous = insert;
            this.header = insert;
        }
        count++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        Node temp = this.tail;
        Node insert = new Node(val);
        if (temp == null) {
            this.tail = insert;
            this.header = insert;
        } else {
            temp.next = insert;
            insert.previous = temp;
            this.tail = insert;
        }
        count++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        Node result = header;
        for (int i = 0; i < index; i++) {
            if (result != null) {
                result = result.next;
            }
        }
        if (result == null) {
            addAtTail(val);
            return;
        }
        Node insert = new Node(val);
        if (header == null) {
            this.header = insert;
            this.tail = insert;
        } else {
            insert.next = result;
            insert.previous = result.previous;
            if (result.previous == null) {
                result.previous = insert;
                this.header = insert;
            } else {
                result.previous.next = insert;
                result.previous = insert;
            }
        }

        count++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= count) {
            return;
        }
        Node result = header;
        for (int i = 0; i < index; i++) {
            if (result != null) {
                result = result.next;
            }
        }
        if (result == null) {
            return;
        }
        if (result.next == null) {
            if (result.previous == null) {
                result = null;
                this.header = null;
                this.tail = null;
            } else {
                result.previous.next = null;
                this.tail = result.previous;
            }
        } else {
            if (result.previous == null) {
                result.next.previous = null;
                this.header = result.next;
            } else {
                result.previous.next = result.next;
                result.next.previous = result.previous;
            }
        }
        count--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */