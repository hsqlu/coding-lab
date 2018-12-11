package algorithm.leetcode.problem.P504;

/**
 * Given an integer, return its base 7 string representation.
 *
 * Example 1:
 * Input: 100
 * Output: "202"
 * Example 2:
 * Input: -7
 * Output: "-10"
 * Note: The input will be in range of [-1e7, 1e7].
 */
public class Solution {
    public String convertToBase7(int num) {
        StringBuilder result = new StringBuilder();
        boolean lessThanZero = false;
        if (num < 0) {
            lessThanZero = true;
            num = Math.abs(num);
        }

        if (num == 0) {
            return "0";
        }

        while (num > 0) {
            result.append(num % 7);
            num = num / 7;
        }
        return lessThanZero ? "-" + result.reverse().toString() : result.reverse().toString();
    }
}
