package leetcode.stack;

import java.util.*;

/**
 * @date 19/06/2018.
 * @author Qiannan Lu
 */
class MinStack {

	Stack<Integer> values = new Stack<>();
	Stack<Integer> minValues = new Stack<>();
	//        Map<Integer, Integer> minValueIndex = new HashMap<>();

	/** initialize your data structure here. */
	public MinStack() {

	}

	public void push(int x) {
		values.push(x);
		if (minValues.isEmpty()) {
			minValues.push(x);
		} else {
			if (minValues.peek() >= x) {
				minValues.push(x);
			} else {
				minValues.push(minValues.peek());
			}
		}
	}

	public void pop() {
		values.pop();
		minValues.pop();
	}

	public int top() {
		return values.peek();
	}

	public int getMin() {
		return minValues.peek();
	}
}