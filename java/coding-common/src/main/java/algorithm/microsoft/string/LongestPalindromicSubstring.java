package algorithm.microsoft.string;

import java.util.Arrays;

/**
 * Longest Palindromic Substring
 * Solution
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 */
public class LongestPalindromicSubstring {


    // Dynamic programming approach
    public String dpLongestPalindrome(String s) {
        if (null == s || s.isEmpty()) {
            return s;
        }
        int l = s.length();
        String ans = "";

        int[][] dp = new int[l][l];

        for (int i = 0; i < l; i++) {
            dp[i][i] = 1;
            if (i + 1 < l) {
                dp[i][i+1] = s.charAt(i) == s.charAt(i+1) ? 1 : 0;
            }
        }

        for (int i = 0; i < l; i++) {                                       // i is the length of possible palindrome char sequence
            for (int j = 0; j + i < l; j++) {
                if (i == 0)
                    dp[j][j+i] = 1;                                         // Base case: all single char are palindrome
                else if (i == 1)
                    dp[j][j+i] = s.charAt(j) == s.charAt(i+j) ? 1 : 0;      // Base case: all pair chars are palindrome if they are the same.
                else
                    dp[j][j+i] = s.charAt(j) == s.charAt(i+j) && dp[j+1][j+i-1] == 1 ? 1 : 0;
                                                                            // All chars from j to j+i are palindrome if only if charAt i equals charAt i+j and chars form j+1 to i+j-1 are palindrome.
                                                                            // And as the length i is bigger than i-2, the dp[j+1][j+i-1] must be initialized in former loop.
                if (dp[j][j+i] == 1 && i + 1 > ans.length()) {
                    ans = s.substring(j, j + i + 1);
                }
            }

        }

        return ans;
    }

    // expand from each char to check the possible longest substring
    public String longestPalindrome(String s) {
        if (null == s || s.isEmpty()) {
            return s;
        }
        int l = s.length();

        String ans = "";
        for (int i = 0; i < l; i++) {
            String odd = expand(s, i, i);
            ans = odd.length() > ans.length() ? odd : ans;
            String even = expand(s, i, i+1);
            ans = even.length() > ans.length() ? even : ans;
        }

        return ans;
    }

    private String expand(String s, int l, int r) {
//        while (l >= 0 && r < s.length()) {
//            if (s.charAt(l) == s.charAt(r)) {
//                l--;
//                r++;
//            } else {
//                break;
//            }
//        }

        // Remember clean code
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return s.substring(l+1, r);
    }

}
