package algorithm.leetcode.problem.P1091;

import java.util.LinkedList;
import java.util.Queue;

import static algorithm.common.Helper.allDirections;

/**
 * 1091. Shortest Path in Binary Matrix
 * Medium
 *
 * 301
 *
 * 33
 *
 * Add to List
 *
 * Share
 * In an N by N square grid, each cell is either empty (0) or blocked (1).
 *
 * A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:
 *
 * Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
 * C_1 is at location (0, 0) (ie. has value grid[0][0])
 * C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
 * If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
 * Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.
 */
public class Solution {
    public static void main(String[] args) {
        new Solution().shortestPathBinaryMatrix(new int[][] {
                {0, 0, 0},
                {1, 1, 0},
                {1, 1, 0}
        });
    }
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (0 == grid.length || 0 == grid[0].length) {
            return 0;
        }
        int n = grid.length;
        int ans = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans++;
            while (size-- > 0) {
                int[] cur = queue.remove();
                int r = cur[0];
                int c = cur[1];
                if (grid[r][c] == 1) {
                    continue;
                }
                if (r == n - 1 && c == n - 1) {
                    return ans;
                }
                grid[r][c] = 1;
                for (int[] dir : allDirections) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr < 0 || nc < 0 || nr >= n || nc >= n) {
                        continue;
                    }
                    queue.add(new int[] {nr, nc});
                }
            }
        }
        return -1;
    }
}
