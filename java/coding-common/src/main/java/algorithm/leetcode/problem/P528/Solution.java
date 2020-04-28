package algorithm.leetcode.problem.P528;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        new Solution().maxSubArray(new int[] {-2, 1});
    }
    public int maxSubArray(int[] nums) {
        if (1 == nums.length) {
            return nums[0];
        }

        int l = nums.length;
        int[] sum = new int[l + 1];
        sum[0] = 0;
        for (int i = 1; i < l; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= l; i++) {
            min = Math.min(min, sum[i - 1]);
            max = Math.max(max, sum[i] - min);
        }

//        Comparator.comparingInt();
        return max;
    }

}
