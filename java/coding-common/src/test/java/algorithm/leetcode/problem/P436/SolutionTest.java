package algorithm.leetcode.problem.P436;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void findRightInterval() {
        new Solution().findRightInterval(new Interval[] {
                new Interval(3, 4),
                new Interval(2, 3),
                new Interval(1, 2),
        });
    }

    @Test
    void binarySearchMinimumGreater() {

        new Solution().binarySearchMinimumGreater(new Integer[]{1, 2, 4}, 0, 2, 3);
    }
}