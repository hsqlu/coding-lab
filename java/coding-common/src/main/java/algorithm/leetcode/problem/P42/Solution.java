package algorithm.leetcode.problem.P42;

import java.util.*;

public class Solution {
    public int trap(int[] height) {
        Deque<Integer> stack = new LinkedList<>();
        int ans = 0;
        return ans;
    }

    LinkedList<Integer> cache = new LinkedList<>();

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new LinkedList<>();


        for (int i = nums.length - 1; i >= 0; i--) {
            ans.add(0, insert(nums[i]));
        }
        return ans;
    }

    private Integer insert(int n) {
        int l = 0, r = cache.size();

        while (l < r) {
            int m = (l + r) / 2;
            if (cache.get(m) < n) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        cache.add(l, n);
        return l;
    }

    public static void main(String[] args) {
        List<Integer> ans = new Solution().countSmaller(new int[] {5,2,6,1});
        System.out.println(ans);
    }
}