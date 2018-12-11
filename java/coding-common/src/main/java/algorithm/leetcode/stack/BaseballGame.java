package algorithm.leetcode.stack;

import java.util.Stack;

/**
 * @date 19/06/2018.
 * @author Qiannan Lu
 */
public class BaseballGame {
	// "+" "D" "C"
	public int calPoints(String[] ops) {
		int sum = 0;
		Stack<Integer> stack = new Stack<>();
		for (String s : ops) {
			switch (s) {
				case "+": {
					int a = stack.pop();
					int c = a + stack.peek();
					sum += c;
					stack.push(a);
					stack.push(c);
					break;
				}
				case "D": {
					int c = stack.peek() * 2;
					sum += c;
					stack.push(c);
					break;
				}
				case "C": {
					int c = stack.pop();
					sum -= c;
					break;
				}
				default: {
					int c = Integer.parseInt(s);
					sum += c;
					stack.push(c);
					break;
				}
			}
		}
		return sum;
	}
}
