package algorithm.leetcode.problem.P93;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        backtracking(0, s, ans, new StringBuilder());
        return ans;
    }

    void backtracking(int index, String s, List<String> ans, StringBuilder sb) {
        if (index == 4 || s.isEmpty()) {
            if (index == 4 && s.isEmpty()) {
                ans.add(sb.toString());
                return;
            }
            return;
        }


        for (int i = 0; i <= 2 && i < s.length(); i++) {
            // if a part start with 0, don't parse any more chars starts with 0
            if (i > 0 && s.charAt(0) == '0') {
                break;
            }
            String left = s.substring(0, i+1);
            if (Integer.parseInt(left) > 255) {
                continue;
            }
            if (sb.length() != 0) {
                left = "." + left;
            }
            sb.append(left);
            backtracking(index + 1, s.substring(i+1), ans, sb);
            sb.delete(sb.length()-left.length(), sb.length());
        }
    }
}
