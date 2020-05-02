package algorithm.microsoft.string;

/**
 * Valid Palindrome
 * Solution
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 *
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 *
 * Example 1:
 *
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * Example 2:
 *
 * Input: "race a car"
 * Output: false
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if (null == s || 0 == s.length()) {
            return true;
        }
        char[] chars = s.toLowerCase().toCharArray();
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (!Character.isLetterOrDigit(chars[l])) {
                l++;
                continue;   // Remember the continue;
            }
            if (!Character.isLetterOrDigit(chars[r])) {
                r--;
                continue;   // Remember the continue;
            }
            if (chars[l] == chars[r]) {
                l++;
                r--;
            } else {
                return false;
            }
        }

        return true;
    }
}
