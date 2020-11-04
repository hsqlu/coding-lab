package algorithm.microsoft.backtracking;

import java.util.HashMap;

public class RegularExpressionMatching {
    public static void main(String[] args) {
        new RegularExpressionMatching().isMatch("aa", "a*");
    }
    public boolean isMatch(String s, String p) {
        if (null == p || p.isEmpty()) {
            return null == s || s.isEmpty();
        }

        int is = 0, ip = 0;

        return isMatch(s, is, p, ip, new HashMap<>());
    }

    private boolean isMatch(String s, int is, String p, int ip, HashMap<String, Boolean> cache) {
        if (ip >= p.length() && is >= s.length()) {
            return true;
        }
        if (is >= s.length()) {
            if (p.length() > ip + 1 && p.charAt(ip+1) == '*') {
                return isMatch(s, is, p, ip+2, cache);
            }
            return false;
        }
        if (ip >= p.length()) {
            return false;
        }

        String key = is + "" + ip;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        boolean result;
        boolean first = s.charAt(is) == p.charAt(ip) || p.charAt(ip) == '.';
        if (p.length() > ip + 1 && p.charAt(ip + 1) == '*') {
            result = isMatch(s, is, p, ip + 2, cache) || (first && isMatch(s, is + 1, p, ip, cache));
        } else {
            result = first && isMatch(s, is + 1, p, ip + 1, cache);
        }
        cache.put(key, result);
        return result;
    }
}
