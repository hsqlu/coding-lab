package algorithm.leetcode.problem.P253;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * P253 Meeting Rooms II
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1, e1], [s1, e2], ...] (si < ei),
 * find the minimum number of conference rooms required.
 *
 * Example 1:
 *
 * Input: [[0, 30], [5, 10], [15, 20]]
 * Output: 2
 *
 * Example 2:
 *
 * Input: [[7, 10], [2, 4]]
 * Output: 1
 *
 * class Interval {
 *     int start;
 *     int end;
 *     Interval() {
 *         start = 0; end = 0;
 *     }
 * }
 *
 */
public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if (null == intervals || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a.start));
//        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        PriorityQueue<Interval> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.end));
//        PriorityQueue<Interval> minHeap = new PriorityQueue<>((a, b) -> a.end - b.end);
        minHeap.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Interval current = intervals[i];
            Interval earliest = minHeap.remove();
            if (current.start >=  earliest.end) {
                earliest.end = current.end;
            } else {
                minHeap.add(current);
            }
            minHeap.add(earliest);
        }
        return minHeap.size();
    }

    class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}


