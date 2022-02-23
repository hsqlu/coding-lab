package algorithm.leetcode.problem.P416;

import java.util.HashMap;
import java.util.Map;

/**
 * 416. Partition Equal Subset Sum
 * Medium
 *
 * Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 * Example 2:
 *
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 200
 *     1 <= nums[i] <= 100
 */
public class Solution {
    Map<String, Boolean> cache = new HashMap<>();

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;
        return dp(nums, 0, sum);
    }

    public boolean dp(int[] nums, int index, int sum) {
        if (index >= nums.length) {
            return false;
        }

        if (cache.containsKey(index + "-" + sum)) {
            return cache.get(index + "-" + sum);
        }
        boolean res;
        if (nums[index] == sum) {
            res = true;
        } else if (nums[index] < sum) {
            res = dp(nums, index + 1, sum - nums[index]) || dp(nums, index + 1, sum);
        } else {
            res = dp(nums, index + 1, sum);
        }
        cache.put(index + "-" + sum, res);
        return res;
    }
}
