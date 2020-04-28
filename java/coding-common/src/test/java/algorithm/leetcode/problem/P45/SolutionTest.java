package algorithm.leetcode.problem.P45;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void jump() {
        new Solution().jump(new int[] {2, 1, 1, 1, 1});

        new Solution().jump(new int[] {5,9,3,2,1,0,2,3,3,1,0,0});
    }
}