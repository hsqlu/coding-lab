package leetcode.problem.P470;

/**
 * 479. Largest Palindrome Product
 * <p>
 * <p>
 * Find the largest palindrome made from the product of two n-digit numbers.
 * <p>
 * Since the result could be very large, you should return the largest palindrome mod 1337.
 * <p>
 * Example:
 * <p>
 * Input: 2
 * <p>
 * Output: 987
 * <p>
 * Explanation: 99 x 91 = 9009, 9009 % 1337 = 987
 * <p>
 * Note:
 * <p>
 * The range of n is [1,8].
 */
public class Solution {
    public static void main(String[] args) {
        new Solution().largestPalindrome(2);
    }

    public int largestPalindrome(int n) {
        if (n == 1) return 9;
        int max = (int) Math.pow(10, n) - 1;
        for (int v = max - 1; v > max / 10; v--) {
            long u = Long.valueOf(v + new StringBuilder().append(v).reverse().toString());
            for (long x = max; x * x >= u; x--)
                if (u % x == 0)
                    return (int) (u % 1337);
        }
        return 0;
    }
}
