package leetcode.stack;

import java.util.Stack;

/**
 * @date 19/06/2018.
 * @author Qiannan Lu
 */
public class ValidParentheses {
	public boolean isValid(String s) {

		Stack<Character> stack = new Stack<>();
		for(char c : s.toCharArray()) {
			switch (c) {
				case '[' :
					stack.push(c);
					break;
				case '{' :
					stack.push(c);
					break;
				case '(' :
					stack.push(c);
					break;
				case ']' :
					if (stack.isEmpty() || stack.pop() != '[') {
						return false;
					}
					break;
				case '}' :
					if (stack.isEmpty() || stack.pop() != '{') {
						return false;
					}
					break;
				case ')' :
					if (stack.isEmpty() || stack.pop() != '(') {
						return false;
					}
					break;
			}
		}
		return stack.isEmpty();
	}

	public boolean anotherIsValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (char c : s.toCharArray()) {
			if (c == '(')
				stack.push(')');
			else if (c == '{')
				stack.push('}');
			else if (c == '[')
				stack.push(']');
			else if (stack.isEmpty() || stack.pop() != c)
				return false;
		}
		return stack.isEmpty();
	}
}
