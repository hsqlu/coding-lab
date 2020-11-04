package algorithm.microsoft.backtracking;

import java.util.HashMap;

public class WildcardMatching {

    public boolean isMatch(String s, String p) {
        if (null == p || p.isEmpty()) {
            return null == s || s.isEmpty();
        }

        int is = 0, ip = 0;

        return isMatch(s, is, p, ip, new HashMap<>());
    }

    private boolean isMatch(String s, int is, String p, int ip, HashMap<String, Boolean> cache) {
        if (is >= s.length()) {
            while (ip < p.length()) {
                if (p.charAt(ip++) != '*') {
                    return false;
                }
            }
            return true;
        }
        if (ip >= p.length()) {
            return is >= s.length();
        }
        String key = is + "" + ip;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        boolean result;
        switch (p.charAt(ip)) {
            case '?':
                result = isMatch(s, is + 1, p, ip + 1, cache);
                break;
            case '*':
                result = isMatch(s, is, p, ip + 1, cache) || isMatch(s, is + 1, p, ip + 1, cache) || isMatch(s, is + 1, p, ip, cache);
                break;
            default:
                result = s.charAt(is) == p.charAt(ip) && isMatch(s, is + 1, p, ip + 1, cache);
                break;
        }
        cache.put(key, result);
        return result;
    }
}
