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
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits == null || digits.length() == 0) {
            return result;
        }
        char[] chars = digits.toCharArray();

        String[] mapping = new String[] {
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqrs",
                "tuv",
                "wxyz"
        };

//        for (int i = 0; i < chars.length; i++) {
//            char[] starts = mapping[chars[i] - '2'].toCharArray();
//            for (char s : starts) {
//                if (digits.substring(i + 1).length() == 0) {
//                    result.add(String.valueOf(s));
//                } else {
//                    List<String> res = letterCombinations(digits.substring(i+1));
//                    for (String subRes : res) {
//                        result.add(s + subRes);
//                    }
//                }
//            }
//        }
        char[] starts = mapping[chars[0] - '2'].toCharArray();
        if (digits.length() == 1) {
            for (char c : starts) {
                result.add(String.valueOf(c));
            }
        } else {
            for (char c : chars) {
                List<String> res = letterCombinations(digits.substring(1));
                for (String s : res) {
                    result.add(c + s);
                }
            }
        }

        return result;
    }

}
