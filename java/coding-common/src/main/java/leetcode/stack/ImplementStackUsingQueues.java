package leetcode.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @date 19/06/2018.
 * @author Qiannan Lu
 */
public class ImplementStackUsingQueues {
}

class MyStack {
	Queue<Integer> queue = new LinkedList<>();
	Queue<Integer> transfer = new LinkedList<>();

	/** Initialize your data structure here. */
	public MyStack() {

	}

	/** Push element x onto stack. */
	public void push(int x) {
		queue.offer(x);
	}

	/** Removes the element on top of the stack and returns that element. */
	public int pop() {
		return queue.poll();
	}

	/** Get the top element. */
	public int top() {
		return queue.peek();
	}

	/** Returns whether the stack is empty. */
	public boolean empty() {
		return queue.isEmpty();
	}
}