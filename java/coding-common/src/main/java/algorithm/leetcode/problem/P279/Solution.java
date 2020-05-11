package algorithm.leetcode.problem.P279;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * 279. Perfect Squares
 * Medium
 *
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 *
 * Example 1:
 *
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 *
 * Example 2:
 *
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 */
public class Solution {

    // Using db table;
    public int numSquares(int n) {

        List<Integer> ps = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            ps.add(i * i);
        }

        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            if (ps.contains(i)) {
                dp[i] = 1;
            } else {
                int count = Integer.MAX_VALUE;

                for (int s : ps) {
                    if (i - s > 0) {
                        count = Math.min(count, dp[i - s] + 1);
                    }
                }
                dp[i] = count;
            }

        }

        return dp[n];
    }

    public static void main(String[] args) {
        bfsNsumSquares(12);
    }
    public static int bfsNsumSquares(int n) {
        List<Integer> squares = new Solution().generateSquares(100);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] marked = new boolean[n+1];
        queue.add(n);
        int level = 0;

        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.remove();
                for (int s : squares) {
                    int next = cur - s;
                    if (next < 0) {
                        break;
                    }
                    if (next == 0) {
                        return level;
                    }
                    if (marked[next]) {
                        continue;
                    }
                    marked[next] = true;
                    queue.add(next);
                }
            }
        }

        return level;
    }
    private List<Integer> generateSquares(int n) {
        List<Integer> ans = new ArrayList<>();

        int square = 1;
        int diff = 3;

        while (square <= n) {
            ans.add(square);
            square += diff;
            diff += 2;
        }
        return ans;
    }
}
