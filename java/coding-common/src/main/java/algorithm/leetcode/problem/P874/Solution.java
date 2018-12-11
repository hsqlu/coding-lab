package algorithm.leetcode.problem.P874;

/**
 * 874. Walking Robot Simulation
 * Easy
 * 50
 * 283
 * <p>
 * <p>
 * A robot on an infinite grid starts at point (0, 0) and faces north.  The robot can receive one of three possible types of commands:
 * <p>
 * -2: turn left 90 degrees
 * -1: turn right 90 degrees
 * 1 <= x <= 9: move forward x units
 * Some of the grid squares are obstacles.
 * <p>
 * The i-th obstacle is at grid point (obstacles[i][0], obstacles[i][1])
 * <p>
 * If the robot would try to move onto them, the robot stays on the previous grid square instead (but still continues following the rest of the route.)
 * <p>
 * Return the square of the maximum Euclidean distance that the robot will be from the origin.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: commands = [4,-1,3], obstacles = []
 * Output: 25
 * Explanation: robot will go to (3, 4)
 * Example 2:
 * <p>
 * Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 * Output: 65
 * Explanation: robot will be stuck at (1, 4) before turning left and going to (1, 8)
 * <p>
 * <p>
 * Note:
 * <p>
 * 0 <= commands.length <= 10000
 * 0 <= obstacles.length <= 10000
 * -30000 <= obstacle[i][0] <= 30000
 * -30000 <= obstacle[i][1] <= 30000
 * The answer is guaranteed to be less than 2 ^ 31.
 */
public class Solution {

    public static void main(String[] args) {
        new Solution().robotSim(new int[]{2, -1, 8, -1, 6}, new int[][]{
                {1, 5}, {-5, -5}, {0, 4}, {-1, -1}, {4, 5}, {-5, -3}, {-2, 1}, {-2, -5}, {0, 5}, {0, -1}
        });
    }

    class Robot {
        private int direction = 0;
        private int x = 0;
        private int y = 0;
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        Robot robot = new Robot();
        for (int i : commands) {
            if (i == -2) {
                if (robot.direction == -2) {
                    robot.direction = 1;
                } else
                    robot.direction--;
            } else if (i == -1) {
                if (robot.direction == 1) {
                    robot.direction = -2;
                } else
                    robot.direction++;
            } else {
                int x = robot.x;
                int y = robot.y;
                if (robot.direction == 0) {
                    int ob = Integer.MAX_VALUE;
                    boolean update = false;
                    for (int m = 0; m < obstacles.length; m++) {
                        if (obstacles[m][0] != x) {
                            continue;
                        }
                        if (obstacles[m][1] > y && obstacles[m][1] <= y + i) {
                            update = true;
                            ob = Math.min(obstacles[m][1], ob);
                        }
                    }
                    if (update) {
                        robot.y = ob - 1;
                    } else {
                        robot.y = robot.y + i;
                    }
                } else if (robot.direction == 1) {
                    int ob = Integer.MAX_VALUE;
                    boolean update = false;
                    for (int m = 0; m < obstacles.length; m++) {
                        if (obstacles[m][1] != y) {
                            continue;
                        }
                        if (obstacles[m][0] > x && obstacles[m][0] <= x + i) {
                            update = true;
                            ob = Math.min(obstacles[m][0], ob);
                        }
                    }
                    if (update) {
                        robot.x = ob - 1;
                    } else {
                        robot.x = robot.x + i;
                    }
                } else if (robot.direction == -1) {
                    int ob = Integer.MIN_VALUE;
                    boolean update = false;
                    for (int m = 0; m < obstacles.length; m++) {
                        if (obstacles[m][1] != y) {
                            continue;
                        }
                        if (obstacles[m][0] < x && obstacles[m][0] >= x - i) {
                            update = true;
                            ob = Math.max(obstacles[m][0], ob);
                        }
                    }
                    if (update) {
                        robot.x = ob + 1;
                    } else {
                        robot.x = robot.x - i;
                    }
                } else if (robot.direction == -2) {
                    int ob = Integer.MIN_VALUE;
                    boolean update = false;
                    for (int m = 0; m < obstacles.length; m++) {
                        if (obstacles[m][0] != x) {
                            continue;
                        }
                        if (obstacles[m][1] < y && obstacles[m][1] >= y - i) {
                            update = true;
                            ob = Math.max(obstacles[m][1], ob);
                        }
                    }
                    if (update) {
                        robot.y = ob + 1;
                    } else {
                        robot.y = robot.y - i;
                    }
                }
            }
        }

        return robot.x * robot.x + robot.y * robot.y;
    }
}
