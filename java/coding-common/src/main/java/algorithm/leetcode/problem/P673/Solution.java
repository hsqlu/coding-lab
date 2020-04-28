package algorithm.leetcode.problem.P673;

/**
 * 673. Number of Longest Increasing Subsequence
 * Medium
 *
 * Given an unsorted array of integers, find the number of longest increasing subsequence.
 *
 * Example 1:
 *
 * Input: [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
 *
 * Example 2:
 *
 * Input: [2,2,2,2,2]
 * Output: 5
 * Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
 *
 * Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.
 */
public class Solution {

    public int findNumberOfLIS(int[] nums) {
        int count = 0;

        if (null == nums || 0 == nums.length) {
            return count;
        }
        int n = nums.length;
        int[] dp = new int[n];
        int max = len(nums, dp);

        int[] cp = new int[n];
        for(int i = 0; i < n; ++i) {
            if(dp[i] == max) {
                count += count(nums, cp, dp, i);
            }
        }

        return count;
    }

    public int len(int[] nums, int[] dp, int n) {
        if (n == 0) return 0;
        if (dp[n] > 0) return dp[n];

        int max = 1;
        for (int i = 0; i < n; i++) {
            if (nums[n] > nums[i]) {
                max = Math.max(max, len(nums, dp, i) + 1);
            }
        }
        dp[n] = max;
        return max;
    }


    public int len(int[] nums, int[] dp) {
        if (nums.length == 0) {
            return 0;
        }
        int max = 1;
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                dp[i] = dp [i - 1];
            } else {
                int n = i - 1;
                while (n >= 0) {
                    if (nums[n] > nums[i]) {
                        n--;
                        continue;
                    }
                    if (nums[n] == nums[i]) {
                        dp[i] = dp[n];
                    } else {
                        dp[i] = dp[n] + 1;
                    }
                    break;
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int count(int[] nums, int[] dp, int[] cp, int max) {
        if (max == 0) {
            return 1;
        }
        if (cp[max] > 0) return cp[max];

        int sum = 0;
        int l = dp[max];


        for (int i = 0; i < max; i++) {
            if (nums[max] > nums[i] && dp[i] == max - 1) {
                sum += count(nums, dp, cp, i);
            }
        }
        if (sum == 0) {
            sum = 1;
        }
        cp[max] = sum;
        return sum;
    }
}
