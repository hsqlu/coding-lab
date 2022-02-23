package algorithm.leetcode.problem.P91;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
    }

    Map<String, Integer> cache = new HashMap<>();
    public int numDecodings(String s) {
        if (s == null || s.isEmpty() || s.charAt(0) == '0')
            return 0;
        if (s.length() == 1) {
            return 1;
        }
        if (cache.containsKey(s)) {
            return cache.get(s);
        }
        int ans = 0;
        int c = Integer.parseInt(s.substring(0, 2));
        if (c <= 26) {
            ans = numDecodings(s.substring(1)) +numDecodings(s.substring(2));
        } else {
            ans = numDecodings(s.substring(1));
        }
        cache.put(s, ans);
        return ans;
    }

    private int calculate(String s, int i) {
        if (i >= s.length()) {
            return 1;
        }
        if (s.charAt(i) == '0') {
            return 0;
        }
        String sub = s.substring(i);
        if ((i+1) < s.length() && Integer.parseInt(s.substring(i, i+2)) <= 26) {
            String s1 = s.substring(i + 1);
            String s2 = s.substring(i + 2);

            return calculate(s, i + 1) + calculate(s, i + 2);
        }
        return calculate(s, i + 1);
    }
}
