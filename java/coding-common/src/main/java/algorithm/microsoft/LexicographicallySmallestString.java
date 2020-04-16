package algorithm.microsoft;

/**
 * Lexicographically smallest string formed by removing at most one character.
 * <p>
 * Example 1:
 * <p>
 * Input: "abczd"
 * Output: "abcd"
 */
public class LexicographicallySmallestString {
    public String solution(String s) {
        int i = 0;
        for (i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                break;
            }
        }
        return new StringBuilder(s).deleteCharAt(i).toString();
    }
}
