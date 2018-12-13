package algorithm.leetcode.problem.P436;

/**
 * 436. Find Right Interval
 * Medium
 * 160
 * 76
 * Favorite
 * Share
 * Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, which can be called that j is on the "right" of i.
 *
 * For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.
 *
 * Note:
 * You may assume the interval's end point is always bigger than its start point.
 * You may assume none of these intervals have the same start point.
 * Example 1:
 * Input: [ [1,2] ]
 *
 * Output: [-1]
 *
 * Explanation: There is only one interval in the collection, so it outputs -1.
 * Example 2:
 * Input: [ [3,4], [2,3], [1,2] ]
 *
 * Output: [-1, 0, 1]
 *
 * Explanation: There is no satisfied "right" interval for [3,4].
 * For [2,3], the interval [3,4] has minimum-"right" start point;
 * For [1,2], the interval [2,3] has minimum-"right" start point.
 * Example 3:
 * Input: [ [1,4], [2,3], [3,4] ]
 *
 * Output: [-1, 2, -1]
 *
 * Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
 * For [2,3], the interval [3,4] has minimum-"right" start point.
 */

import java.util.TreeMap;

/**
 * Solution by myself
 */
public class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        int[] result = new int[intervals.length];
        TreeMap<Integer, Integer> holder = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++) {
            holder.put(intervals[i].start, i);
        }

        // TreeMap has such a method!!!!!
        holder.higherEntry(1);

        Integer[] arr = holder.keySet().toArray(new Integer[holder.keySet().size()]);

        for (int i = 0; i < intervals.length; i++) {
            int index = binarySearchMinimumGreater(arr, 0, arr.length - 1, intervals[i].end );
            if (intervals[i].end <= arr[index]) {
                result[i] = holder.get(arr[index]);
            } else {
                if (index + 1 < arr.length) {
                    result[i] = holder.get(arr[index + 1]);
                } else {
                    result[i] = -1;
                }
            }
        }

        return result;
    }

    public int binarySearchMinimumGreater(Integer[] array, int start, int end, int target) {
        if (target <= array[start]) {
            return start;
        }
        if (start + 1 >= end) {
            return end;
        }

        int mid = start + (end -start) / 2;
        if (array[mid] == target) {
            return mid;
        } else if (array[mid] > target) {
            return binarySearchMinimumGreater(array, start, mid, target);
        } else {
            return binarySearchMinimumGreater(array, mid, end, target);
        }
    }
}
