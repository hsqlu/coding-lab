package algorithm.leetcode.problem.P3;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. Longest Substring Without Repeating Characters
 * Medium
 *
 * 8348
 *
 * 506
 *
 * Add to List
 *
 * Share
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int min = Integer.MIN_VALUE;
        int left = 0, right = 0;

        Set<Character> window = new HashSet<>();
        char[] arr = s.toCharArray();
        while (right < arr.length) {
            if (!window.contains(arr[right])) {
                window.add(arr[right]);
                right++;
            } else {
                min = Math.max(window.size(), min);
                while (window.contains(arr[right])) {
                    window.remove(arr[left]);
                    left++;
                }
            }
        }
        min = Math.max(window.size(), min);

        return min;
    }
}
