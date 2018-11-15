package leetcode.problem.P867;

/**
 * Given a matrix A, return the transpose of A.
 * <p>
 * The transpose of a matrix is the matrix flipped over it's main diagonal, switching the row and column indices of the matrix.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[1,4,7],[2,5,8],[3,6,9]]
 * Example 2:
 * <p>
 * Input: [[1,2,3],[4,5,6]]
 * Output: [[1,4],[2,5],[3,6]]
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 1000
 * 1 <= A[0].length <= 1000
 */
public class Solution {

    public int[][] transpose(int[][] A) {
        int i = A.length;
        int j = A[0].length;
        int[][] result = new int[j][i];

        for (int m = 0; m < j; m++) {
            for (int n = 0; n < i; n++) {
                result[m][n] = A[j - (j - n)][i - (i- m)];
            }
        }
        return result;
    }
}
