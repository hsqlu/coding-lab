package algorithm.leetcode;

import java.util.AbstractMap;
import java.util.Map;

public class Pair<F, S> {
    private F first;
    private S second;

    private final Map.Entry<F, S> holder;

    public Pair(F first, S second) {
        holder = new AbstractMap.SimpleEntry<>(first, second);
    }

    public F getFirst() {
        return holder.getKey();
    }

    public S getSecond() {
        return holder.getValue();
    }

    public void setSecond(S second) {
        holder.setValue(second);
    }
}
