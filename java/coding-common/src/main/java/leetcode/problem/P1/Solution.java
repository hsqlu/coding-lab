package leetcode.problem.P1;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * Example:
 *
 * Given nums = [2, 7, 11, 15], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 *
 */
public class Solution {
    public static void main(String[] args) {
        new Solution().twoSum(new int[] {3, 2, 4}, 6);
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> mid = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            Integer index = mid.get(target - nums[i]);
            if (index != null && index != i) {
                return new int[]{i, index};
            }
            mid.put(nums[i], i);
        }
        return null;
    }
}
