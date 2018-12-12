package com.hsqlu.coding.raft;

public class RaftNode {
    private State currentState;
    private long currentTerm;
    private long electionTimeout;

    public RaftNode() {
        this.currentTerm = 0;
        this.currentState = State.FOLLOWER;
    }

    public RaftNode(int i) {

    }


    public void listen() {
        switch (currentState) {
            case LEADER: {

            }
            case FOLLOWER: {

            }
            case CANDIDATE: {

            }
        }
    }

    public void call() {
        switch (currentState) {
            case LEADER: {
                heartbeat();
            }
            case FOLLOWER: {

            }
            case CANDIDATE: {

            }
        }
    }

    private void heartbeat() {

    }

    public void start() {


    }
}
