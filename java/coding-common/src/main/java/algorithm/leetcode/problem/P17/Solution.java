package algorithm.leetcode.problem.P17;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 17. Letter Combinations of a Phone Number
 * Medium
 *
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 * Example:
 *
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * Note:
 *
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
public class Solution {

    private static char[][] holder = {
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();

        if (null == digits || digits.isEmpty()) {
            return ans;
        }
        backtracking(digits, 0, "", ans);
        return ans;
    }

    void backtracking(String digits, int index, String current, List<String> ans) {
        if (index >= digits.length()) {
            ans.add(current);
            return;
        }

        int charsIndex = digits.charAt(index) - '2';

        for (char c : holder[charsIndex]) {
            String sb = current + c;
            backtracking(digits,index+1, sb, ans);
        }
    }
}
