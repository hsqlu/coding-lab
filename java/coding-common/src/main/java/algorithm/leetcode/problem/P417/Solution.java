package algorithm.leetcode.problem.P417;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static algorithm.common.Helper.edgeDirections;

/**
 * 417. Pacific Atlantic Water Flow
 * Medium
 * <p>
 * 1094
 * <p>
 * 220
 * <p>
 * Add to List
 * <p>
 * Share
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
 * <p>
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
 * <p>
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 * <p>
 * Note:
 * <p>
 * The order of returned grid coordinates does not matter.
 * Both m and n are less than 150.
 * <p>
 * <p>
 * Example:
 * <p>
 * Given the following 5x5 matrix:
 * <p>
 * Pacific ~   ~   ~   ~   ~
 * ~  1   2   2   3  (5) *
 * ~  3   2   3  (4) (4) *
 * ~  2   4  (5)  3   1  *
 * ~ (6) (7)  1   4   5  *
 * ~ (5)  1   1   2   4  *
 * *   *   *   *   * Atlantic
 * <p>
 * Return:
 * <p>
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */

public class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {

        List<List<Integer>> result = new ArrayList<>();

        if (0 == matrix.length || 0 == matrix[0].length) {
            return result;
        }
        int m = matrix.length, n = matrix[0].length;
        boolean[][] reachP = new boolean[m][n];
        boolean[][] reachA = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            dfs(matrix, i, 0, reachP);
            dfs(matrix, i, n-1, reachA);
        }
        for (int i = 0; i < n; i++) {
            dfs(matrix, 0, i, reachP);
            dfs(matrix, m-1, i, reachA);
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (reachP[i][j] && reachA[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }


    void dfs(int[][] matrix, int i, int j, boolean[][] reach) {

        reach[i][j] = true;

        for (int[] d : edgeDirections) {
            int nr = i + d[0];
            int nc = j + d[1];

            if (nr < 0 || nc < 0 || nr >= matrix.length || nc >= matrix[0].length || matrix[nr][nc] < matrix[i][j] || reach[nr][nc]) {
                continue;
            }

            dfs(matrix, nr, nc, reach);
        }
    }
}
