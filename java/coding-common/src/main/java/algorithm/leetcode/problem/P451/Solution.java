package algorithm.leetcode.problem.P451;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {

    public String frequencySort(String s) {
        Map<Character, Integer> holder = new HashMap<>();

        for (char c : s.toCharArray()) {
            int count = holder.getOrDefault(c, 0);
            holder.put(c, count+1);
        }

        Map.Entry<Character, Integer>[] entrys = holder.entrySet().toArray(new Map.Entry[holder.size()]);
        Arrays.sort(entrys, (a, b) -> b.getValue() - a.getValue());
        StringBuilder result = new StringBuilder();

        // Another import way
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> holder.get(b) - holder.get(a));
        maxHeap.addAll(holder.keySet());

        while (!maxHeap.isEmpty()) {
            char c = maxHeap.remove();
            for (int j = 0; j < holder.get(c); j++) {
                result.append(c);
            }
        }

        for (int i = 0; i < entrys.length; i++) {
            int n = entrys[i].getValue();
            for (int j = 0; j < n; j++) {
                result.append(entrys[i].getKey());
            }
        }

        return result.toString();
    }

}
