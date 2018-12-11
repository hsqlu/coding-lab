package algorithm.leetcode.problem.P414;

import java.util.TreeSet;

/**
 * 414. Third Maximum Number
 * Easy
 * 303
 * 496
 *
 *
 * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).
 *
 * Example 1:
 * Input: [3, 2, 1]
 *
 * Output: 1
 *
 * Explanation: The third maximum is 1.
 * Example 2:
 * Input: [1, 2]
 *
 * Output: 2
 *
 * Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
 * Example 3:
 * Input: [2, 2, 3, 1]
 *
 * Output: 1
 *
 * Explanation: Note that the third maximum here means the third maximum distinct number.
 * Both numbers with value 2 are both considered as second maximum.
 */
public class Solution {

    public static void main(String[] args) {
        new Solution().thirdMax(new int[] {1, 2, 2, 5, 3, 5});
    }
    TreeSet<Integer> holder = new TreeSet<>();

    public int thirdMax(int[] nums) {
        for (int i : nums) {
            if (holder.contains(i)) {
                continue;
            }
            if (holder.size() < 3){
                holder.add(i);
                continue;
            }

            if (holder.first() < i) {
                holder.remove(holder.first());
                holder.add(i);
            }
        }
        return holder.size() == 3 ? holder.first() : holder.last();
    }
}
