package algorithm.leetcode.problem.P45;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    Map<Integer, Integer> holder = new HashMap<>();


    public int jump(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        return sjump(nums, 0, nums[0]);
    }
    private int sjump(int[] nums, int start, int longth) {
        int min = Integer.MAX_VALUE;
        if (longth == 0) {
            return min;
        }

        if (start + longth + 1 >= nums.length)
            return 1;

        if (holder.get(start) != null) {
            return holder.get(start);
        }

        for (int i = 1; i <= longth; i++) {
            if (nums[start + i] == 0)
                continue;
            min = Math.min(min, sjump(nums, start + i, nums[start + i]));
        }

        min = min == Integer.MAX_VALUE ? min : min + 1;
        holder.put(start, min );
        return min;
    }
//
//    private int sjump(int[] nums, int start, int longth) {
//        if (start + longth + 1 >= nums.length)
//            return 1;
//
//
//        for (int i = 1; i <= longth; i++) {
//            if (nums[start + i] == 0)
//                continue;
//            step = Math.min(step, sjump(nums, start + i, nums[start + i]));
//        }
//
//        return step + 1;
//    }
}
