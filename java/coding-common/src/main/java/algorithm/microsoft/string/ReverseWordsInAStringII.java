package algorithm.microsoft.string;

/**
 * Reverse Words in a String II
 * Solution
 * Given an input string , reverse the string word by word.
 *
 * Example:
 *
 * Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * Note:
 *
 * A word is defined as a sequence of non-space characters.
 * The input string does not contain leading or trailing spaces.
 * The words are always separated by a single space.
 * Follow up: Could you do it in-place without allocating extra space?
 */
public class ReverseWordsInAStringII {

    public void reverseWords(char[] s) {

        swapArray(s, 0, s.length - 1);

        int start = 0, end = 0;

        for (int i = 0; i <= s.length; i++) {
            if (i == s.length) {    // Remember the last wap without an ending space
                swapArray(s, start, i - 1);
                return;
            } else if (s[i] == ' ') {
                end = i - 1;
                swapArray(s, start, end);
                start = i + 1;
            }
        }
    }

    void swapArray(char[] s, int l, int r) {
        while (l < r) {
            s[l] ^= s[r];
            s[r] ^= s[l];
            s[l] ^= s[r];
            l++;
            r--;
        }
    }
}
