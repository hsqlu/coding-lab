package algorithm.leetcode.problem.P859;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * 859. Buddy Strings
 * Easy
 * 192
 * 108
 *
 *
 * Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.
 *
 *
 *
 * Example 1:
 *
 * Input: A = "ab", B = "ba"
 * Output: true
 * Example 2:
 *
 * Input: A = "ab", B = "ab"
 * Output: false
 * Example 3:
 *
 * Input: A = "aa", B = "aa"
 * Output: true
 * Example 4:
 *
 * Input: A = "aaaaaaabc", B = "aaaaaaacb"
 * Output: true
 * Example 5:
 *
 * Input: A = "", B = "aa"
 * Output: false
 *
 *
 * Note:
 *
 * 0 <= A.length <= 20000
 * 0 <= B.length <= 20000
 * A and B consist only of lowercase letters.
 */
public class Solution {

    public boolean buddyStrings(String A, String B) {
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        if (a.length != b.length) {
            return false;
        }
        if (A.equals(B)) {
            Set<Character> result = new HashSet<>();
            for (char anA : a) {
                if (!result.add(anA)) {
                    return true;
                }
            }
            return false;
        }
        int count = 0;
        int[] index = new int[2];
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                count++;
                if (count > 2) {
                    return false;
                }
                index[count - 1] = i;
            }
        }
        return count == 2 && a[index[0]] == b[index[1]] && a[index[1]] == b[index[0]];
    }
}
