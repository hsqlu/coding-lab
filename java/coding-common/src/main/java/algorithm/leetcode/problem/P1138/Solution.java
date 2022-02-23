package algorithm.leetcode.problem.P1138;

public class Solution {
    private int row = 0, col = 0;

    public String alphabetBoardPath(String target) {
        StringBuilder ans = new StringBuilder();

        char[] chars = target.toCharArray();
        for (char c : chars) {
            updatePath(ans, c);
        }
        return ans.toString();
    }

    private void updatePath(StringBuilder ans, char c) {
        int dis = c - 'a';

        int ld = dis % 5;
        int h = dis / 5;

        while(row > h) {
            ans.append("U");
            row--;
        }

        while (col > ld) {
            ans.append("L");
            col--;
        }
        while (col < ld) {
            ans.append("R");
            col++;
        }
        while (row < h) {
            ans.append("D");
            row++;
        }

        ans.append("!");
    }
}
