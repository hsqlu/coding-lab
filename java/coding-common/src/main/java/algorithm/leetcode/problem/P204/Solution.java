package algorithm.leetcode.problem.P204;

/**
 * 204. Count Primes
 * Easy
 * 750
 * 308
 *
 *
 * Count the number of prime numbers less than a non-negative number, n.
 *
 * Example:
 *
 * Input: 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */
public class Solution {

    /**
     * Awesome
     */
    public int countPrimesCool(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {
                count++;
                for (int j = 2; i*j < n; j++) {
                    notPrime[i*j] = true;
                }
            }
        }

        return count;
    }

    public int countPrimes(int n) {
        int count = 0;
        if (n < 11) {
            for (int i = 1; i < n; i++) {
                if (isPrimeNum(i)) {
                    count++;
                }
            }
        } else {
            count += 4;
            for (int i = 9; i < n; i += 2) {
                if (isPrimeNum(i)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isPrimeNum(int n) {
        double sqrt;
        if (n <= 1) {
            return false;
        }
        if (n == 2 || n == 3) {
            return true;
        }
        if (n % 6 != 1 && n % 6 != 5) {
            return false;
        }
        sqrt = Math.sqrt(n);

        for (int i = 5; i <= sqrt; i += 6) {
            if (n % (i) == 0 | n % (i + 2) == 0)
                return false;
        }
        return true;

    }
}
