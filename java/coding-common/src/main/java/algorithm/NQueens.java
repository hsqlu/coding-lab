package algorithm;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    List<char[][]> res = new ArrayList<>();

    public List<char[][]> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        backtrack(board, 0);
        return res;
    }

    private void backtrack(char[][] board, int row) {
        if (row == board.length) {
            res.add(board);
            return;
        }
        int n = board[row].length;

        for (int col = 0; col < n; col++) {
            if (!isValid(board, row, col)) {
                continue;
            }
            board[row][col] = 'Q';
            backtrack(board, row + 1);
            board[row][col] = '.';
        }
    }

    // 是否可以在 [row][col] 位置放 Queen
    private boolean isValid(char[][] board, int row, int col) {
        int n = board.length;
//        for (int k = 0; k < row; k++) {
//            if ('Q' == board[k][col]) {
//                return false;
//            }
//            if ((col - (row - k) >= 0) && board[k][col - (row - k)] == 'Q') {
//                return false;
//            }
//            if (col + (row - k) < n && board[k][col + (row - k)] == 'Q') {
//                return false;
//            }
//        }

        // 检查列冲突
        for (int i = 0; i < n; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // 检查右上方冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // 检查左上方冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public int anotherApproach(int n) {
        if (n < 1) {
            return 0;
        }

        int[] record = new int[n];

        return process(0, record, n);
    }

    private int process(int i, int[] record, int n) {
        if (i == n) {
            return 1;
        }
        int res = 0;

        for (int j = 0; j < n; j++) {
            if (valid(record, i, j)) {
                record[i] = j;
                res += process(i + 1, record, n);
            }
        }
        return res;
    }

    private boolean valid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }
}
