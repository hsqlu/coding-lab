package algorithm.microsoft;

import java.util.HashMap;
import java.util.Map;

/**
 * Write a function:
 *  public int solution(int[] A)
 * tha, given an array A consisting of N integers, returns the maximum sum of two numbers whose digits add up to an equal sum.
 *
 * If there are no two numbers whose digits have an equal sum, the function should return -1.
 *
 * Examples:
 * [51, 71, 17, 42] --> 93
 *
 * [42, 33, 60] --> 102
 *
 * [51, 32, 43] --> -1
 *
 *
 *
 */
public class MaxSumPair {
    public static int solution(int[] A) {
        int maxSum = -1;

        if (null == A || A.length < 2) {
            return maxSum;
        }

        Map<Integer, Integer> cache = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            if (!cache.containsKey(sumDigits(A[i]))) {
                cache.put(sumDigits(A[i]), A[i]);
            } else {
                maxSum = Math.max(maxSum, cache.get(sumDigits(A[i])) + A[i]);

                cache.put(sumDigits(A[i]), Math.max(cache.get(sumDigits(A[i])), A[i]));

            }
        }

        return maxSum;
    }

    private static int sumDigits(int num) {
        num = Math.abs(num);
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num = num / 10;
        }
        return sum;
    }
}
