package com.hsqlu.coding;

class MyLinkedList {
    Node head;
    Node tail;
    int size = 0;

    class Node {
        int val;
        Node pre;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        this.head = new Node(-1);
        this.tail = head;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public Integer get(int index) {
        if (size == 0 || index < 0 || index >= size) {
            return null;
        }
        Node current = head.next;
        while (index > 0) {
            current = current.next;
            index--;
        }
        return current.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        Node cur = new Node(val);
        if (size == 0) {
            head.next = cur;
            cur.pre = head;
            tail = cur;
        } else {
            Node e = head.next;

            head.next = cur;
            cur.pre = head;
            cur.next = e;
            e.pre = cur;

        }
        size++;

    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {

        if (size == 0) {
            addAtHead(val);
        } else {
            Node cur = new Node(val);
            Node t = tail;

            cur.pre = t;
            t.next = cur;
            tail = cur;
            size++;
        }
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index == size) {
            addAtTail(val);
            return;
        }


        Node i = head;

        while (index > 0) {
            i = i.next;
            index--;
        }

        Node add = new Node(val);

        i.next.pre = add;
        add.next = i.next;
        i.next = add;
        add.pre = i;

        size++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        Node i = head;

        while (index > 0) {
            i = i.next;
            index--;
        }

        if (i.next == tail) {
            i.next = null;
            tail = i;
        } else {
            i.next = i.next.next;
            i.next.pre = i;
        }
        size--;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node n = this.head.next;
        while (n != null) {
            sb.append(n.val);
            sb.append(", ");
            n = n.next;
        }
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        MyLinkedList obj = new MyLinkedList();
//        Integer param_1 = obj.get(0);
        obj.addAtHead(4);
        obj.get(1);
        obj.addAtHead(1);
        obj.addAtHead(5);
        obj.deleteAtIndex(3);

        obj.addAtHead(7);
        obj.get(3);
        obj.get(3);
        obj.get(3);
        obj.addAtHead(1);
        obj.deleteAtIndex(4);
    }

//    ["MyLinkedList","addAtHead","get","addAtHead","addAtHead","deleteAtIndex","addAtHead","get","get","get","addAtHead","deleteAtIndex"]
//            [[],[4],[1],[1],[5],[3],[7],[3],[3],[3],[1],[4]]
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