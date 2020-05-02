package algorithm.microsoft.string;

import com.sun.org.apache.regexp.internal.RE;

/**
 * Reverse Words in a String
 * Solution
 * Given an input string, reverse the string word by word.
 *
 *
 *
 * Example 1:
 *
 * Input: "the sky is blue"
 * Output: "blue is sky the"
 * Example 2:
 *
 * Input: "  hello world!  "
 * Output: "world! hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * Example 3:
 *
 * Input: "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 *
 * Note:
 *
 * A word is defined as a sequence of non-space characters.
 * Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
 * You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 */
public class ReverseWordsInAString {
    public static void main(String[] args) {
        new ReverseWordsInAString().reverseWords(" hello world!  ");
    }
    public String reverseWords(String s) {
        if (null == s || s.length() == 0) {
            return "";
        }
        String[] words = s.trim().split("\\s+");    // Remember trim the string before split, otherwise the first element will be empty string.
        StringBuilder sb = new StringBuilder();

        for (int i = words.length-1; i >= 0; i--) {
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append(words[i]);
        }
        return sb.toString();
    }
}
