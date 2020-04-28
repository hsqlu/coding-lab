package algorithm.leetcode.problem.P1419;

import java.util.Arrays;

public class Solution {
    int[] count = new int[5];

    public static void main(String[] args) {
        new Solution().minNumberOfFrogs("croakcrook");
    }
    public int minNumberOfFrogs(String croakOfFrogs) {
        if (null == croakOfFrogs || 0 == croakOfFrogs.length()) {
            return -1;
        }
        Arrays.fill(count, 0);
        for (char c : croakOfFrogs.toCharArray()) {
            if (!addChar(c))
                return -1;
            if (count[0] == count[1] && count[0] == count[2] && count[0] == count[3] && count[0] == count[4])
                return count[0];
        }
        if (count[0] == count[1] && count[0] == count[2] && count[0] == count[3] && count[0] == count[4]) {
            return count[0];
        }
        else {
            return -1;
        }
    }

    boolean addChar(char c) {
        if (c == 'c') {
            count[0]++;
            return count[0] >= count[1];
        }
        if (c == 'r') {
            count[1]++;
            return count[1] >= count[2] && count[1] <= count[0];
        }
        if (c == 'o') {
            count[2]++;
            return count[2] >= count[3] && count[2] <= count[1];
        }
        if (c == 'a') {
            count[3]++;
            return count[3] >= count[4] && count[3] <= count[2];
        }
        if (c == 'k') {
            count[4]++;
            return count[4] <= count[3];
        }
        return false;
    }
}
