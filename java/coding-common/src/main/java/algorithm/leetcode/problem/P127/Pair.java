package algorithm.leetcode.problem.P127;

public class Pair<T, S> {
    T key;
    S value;

    public Pair(T key, S value) {
        this.key = key;
        this.value = value;
    }


    public T getKey() {
        return key;
    }

    public S getValue() {
        return value;
    }
}
