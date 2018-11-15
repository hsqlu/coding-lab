package leetcode.problem.P766;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.
 * <p>
 * Now given an M x N matrix, return True if and only if the matrix is Toeplitz.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input:
 * matrix = [
 * [1,2,3,4],
 * [5,1,2,3],
 * [9,5,1,2]
 * ]
 * Output: True
 * Explanation:
 * In the above grid, the diagonals are:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
 * In each diagonal all elements are the same, so the answer is True.
 * Example 2:
 * <p>
 * Input:
 * matrix = [
 * [1,2],
 * [2,2]
 * ]
 * Output: False
 * Explanation:
 * The diagonal "[1, 2]" has different elements.
 * <p>
 * Note:
 * <p>
 * matrix will be a 2D array of integers.
 * matrix will have a number of rows and columns in range [1, 20].
 * matrix[i][j] will be integers in range [0, 99].
 * <p>
 * Follow up:
 * <p>
 * What if the matrix is stored on disk, and the memory is limited such that you can only load at most one row of the
 * matrix into the memory at once?
 * What if the matrix is so large that you can only load up a partial row into the memory at once?
 */
public class Solution {
    public static void main(String[] args) {
        new Solution().isToeplitzMatrix(new int[][]{{1, 2, 3, 4}, {5, 1, 2, 3}, {9, 5, 1, 2}});
        new Solution().isToeplitzMatrix(new int[][]{{18}, {66}});
        new Solution().isToeplitzMatrix(new int[][]{{11, 74, 7, 93}, {40, 11, 74, 7}});
    }

    /**
     * We ask what feature makes two coordinates (r1, c1) and (r2, c2) belong to the same diagonal?
     *
     * It turns out two coordinates are on the same diagonal if and only if r1 - c1 == r2 - c2.
     *
     * This leads to the following idea: remember the value of that diagonal as groups[r-c].
     * If we see a mismatch, the matrix is not Toeplitz; otherwise it is.
     */
    public boolean isToeplitzMatrix_Map(int[][] matrix) {
        Map<Integer, Integer> groups = new HashMap();
        for (int r = 0; r < matrix.length; ++r) {
            for (int c = 0; c < matrix[0].length; ++c) {
                if (!groups.containsKey(r - c))
                    groups.put(r - c, matrix[r][c]);
                else if (groups.get(r - c) != matrix[r][c])
                    return false;
            }
        }
        return true;
    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int r = 0; r < matrix.length; ++r)
            for (int c = 0; c < matrix[0].length; ++c)
                if (r > 0 && c > 0 && matrix[r - 1][c - 1] != matrix[r][c])
                    return false;
        return true;
    }

    public boolean isToeplitzMatrix_ByMyselfStupid(int[][] matrix) {

        for (int m = 0; m < matrix.length + matrix[0].length - 1; m++) {
            if (!isTorplitz(matrix, m)) {
                return false;
            }
        }
        return true;
    }

    private boolean isTorplitz(int[][] matrix, int m) {
        Set<Integer> container = new HashSet<>(1);

        if (m < matrix.length) {
            for (int p = 0; (matrix.length - 1 - m) + p < matrix.length && p < matrix[0].length; ++p) {
                container.add(matrix[matrix.length - 1 - m + p][p]);
//                System.out.print(matrix[matrix.length - 1 - m + p][p] + " ");
            }
        } else {
            for (int p = 0; p < matrix.length && p + m - matrix.length + 1 < matrix[0].length; ++p) {
                container.add(matrix[p][p + m - matrix.length + 1]);
//                System.out.print(matrix[p][p + m - matrix.length + 1] + " ");
            }
        }
//        System.out.println();
        return container.size() == 1;
    }
}
