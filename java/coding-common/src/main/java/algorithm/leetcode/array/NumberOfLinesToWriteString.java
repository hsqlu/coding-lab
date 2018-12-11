package algorithm.leetcode.array;

/**
 * @author Qiannan Lu
 * @date 01/06/2018.
 */
public class NumberOfLinesToWriteString {
    public int[] numberOfLines(int[] widths, String S) {
        int size = 100;
        int lines = 1;
        for (char c : S.toCharArray()) {
            if (size >= widths[c - 'a'])
                size -= widths[c - 'a'];
            else {
                lines++;
                size=100;
                size -= widths[c- 'a'];
            }
        }
        return new int[] {lines, 100 - size};
    }

    public static void main(String[] args) {
        new NumberOfLinesToWriteString().numberOfLines(new int[]{10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10},
        "abcdefghijklmnopqrstuvwxyz");
    }
}
