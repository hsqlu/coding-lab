package algorithm.leetcode.problem.P139;

import java.util.*;

public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        HashMap<String, Boolean> cache = new HashMap<>();


        return wordBreak(s, wordSet, cache);
    }

    boolean wordBreak(String s, Set<String> wordSet, Map<String, Boolean> cache) {
        if (wordSet.contains(s)) {
            cache.put(s, true);
            return true;
        }

        if (cache.containsKey(s)) {
            return cache.get(s);
        }

        if (null == s || "".equals(s)) {
            return true;
        }

        for (int i = 1; i < s.length(); i++) {
            if (wordSet.contains(s.substring(i)) && wordBreak(s.substring(0, i), wordSet, cache)) {
                cache.put(s.substring(0, i), true);
                return true;
            }
        }
        cache.put(s, false);
        return false;

    }


}