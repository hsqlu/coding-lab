package algorithm.leetcode.array;

/**
 * @author Qiannan Lu
 * @date 01/06/2018.
 */
public class ShortestDistanceToACharacter {
    public int[] shortestToChar(String S, char C) {
        char[] temp = S.toCharArray();
        int[] result = new int[temp.length];

        int m = S.indexOf(C);
        int n = m;

        for (int i = 0; i < result.length; i++) {
            if (m < 0) {
                result[i] = i - n;
            } else if (i < m) {
                result[i] = m - i > Math.abs(n - i) ?  Math.abs(n - i) : m - i;
            } else {
                n = m;
                m = S.indexOf(C, n + 1);

            }
        }

        return result;
    }

    public static void main(String[] args) {
//        new ShortestDistanceToACharacter().shortestToChar("loveleetcode", 'e');
        new ShortestDistanceToACharacter().shortestToChar("aaba", 'b');
    }
}
