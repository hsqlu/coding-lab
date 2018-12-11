package algorithm.leetcode.array;

/**
 * @author Qiannan Lu
 * @date 01/06/2018.
 */
public class FlippingAnImage {
    public int[][] flipAndInvertImage(int[][] A) {
        int[][] result = new int[A.length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                result[i][j] = 1 - A[i][A.length - j - 1];
            }
        }
        return result;
    }
}
