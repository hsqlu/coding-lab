package algorithm.leetcode.problem.P1578;

//import com.google.common.base.Strings;

public class Solution {
    public int minCost(String colors, int[] neededTime) {
        if (colors == null || colors.length() == 0) {
            return 0;
        }

        int ans = 0;
        char[] chars = colors.toCharArray();
        int current = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[current] == chars[i]) {
                if (neededTime[current] > neededTime[i]) {
                    ans += neededTime[i];
                } else {
                    ans += neededTime[current];
                    current = i;
                }
            } else {
                current = i;
            }
        }
        return ans;
    }
}
