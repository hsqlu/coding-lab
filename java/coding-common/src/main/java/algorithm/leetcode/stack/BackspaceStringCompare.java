package algorithm.leetcode.stack;

import java.util.Stack;

/**
 * @date 19/06/2018.
 * @author Qiannan Lu
 */
public class BackspaceStringCompare {
	public static void main(String[] args) {
		new BackspaceStringCompare().backspaceCompare("ab#c",
				"ad#c");
	}
	public boolean backspaceCompare(String S, String T) {
		Stack<Character> sChars = new Stack<>();
		Stack<Character> tChars = new Stack<>();
		for (Character character : S.toCharArray()) {
			if (character == '#') {
				if (!sChars.isEmpty()) {
					sChars.pop();
				}
			} else {
				sChars.push(character);
			}
		}

		for (Character character : T.toCharArray()) {
			if (character == '#') {
				if (!tChars.isEmpty()) {
					tChars.pop();
				}
			} else {
				tChars.push(character);
			}
		}
//		return String.valueOf(sChars).equals(String.valueOf(true));
		if (sChars.size() == tChars.size()) {
			while (!sChars.isEmpty()) {
				if (sChars.pop() != tChars.pop()) {
					return false;
				}
			}
			return true;
		}

		return false;
	}

	public boolean anotherBackspaceCompare(String S, String T) {
		int i = S.length() - 1, j = T.length() - 1;
		int skipS = 0, skipT = 0;

		while (i >= 0 || j >= 0) { // While there may be chars in build(S) or build (T)
			while (i >= 0) { // Find position of next possible char in build(S)
				if (S.charAt(i) == '#') {skipS++; i--;}
				else if (skipS > 0) {skipS--; i--;}
				else break;
			}
			while (j >= 0) { // Find position of next possible char in build(T)
				if (T.charAt(j) == '#') {skipT++; j--;}
				else if (skipT > 0) {skipT--; j--;}
				else break;
			}
			// If two actual characters are different
			if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
				return false;
			// If expecting to compare char vs nothing
			if ((i >= 0) != (j >= 0))
				return false;
			i--; j--;
		}
		return true;
	}
}
