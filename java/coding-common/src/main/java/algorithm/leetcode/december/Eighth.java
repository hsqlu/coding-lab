package algorithm.leetcode.december;

import java.util.*;

public class Eighth {
    public int numPairsDivisibleBy60(int[] time) {
        int ans = 0;
        Map<Integer, List<Integer>> holder = new HashMap<>();

        for (int i = 0; i < time.length; i++) {
            int res = time[i] % 60;
            holder.putIfAbsent(res, new ArrayList<>());
            holder.get(res).add(i);
        }

        for (int i = 0; i < time.length; i++) {
            int res = (60 - time[i] % 60) % 60;
            List<Integer> cache = holder.get(res);
            if (cache == null || cache.isEmpty()) {
                continue;
            }
            int index = i;
            ans += cache.stream().filter(s -> s > index).count();
        }
        return ans;
    }

    public static void main(String[] args) {
        new Eighth().numPairsDivisibleBy60(new int[] {
                30,20,150,100,40
        });
    }
}
