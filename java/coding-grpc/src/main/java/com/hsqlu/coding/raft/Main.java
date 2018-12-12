package com.hsqlu.coding.raft;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            RaftNode node = new RaftNode(i);
            node.start();
        }
    }
}
