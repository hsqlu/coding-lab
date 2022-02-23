package algorithm.leetcode.problem.P474;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        Arrays.sort(args, Comparator.comparingInt(String::length));
        Arrays.stream(args).sorted(Comparator.comparingInt(String::length)).toArray(String[]::new);
    }
}
