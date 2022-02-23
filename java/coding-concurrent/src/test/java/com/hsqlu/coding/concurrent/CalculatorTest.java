package com.hsqlu.coding.concurrent;

import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created: 2016/4/26.
 * Author: Qiannan Lu
 */
public class CalculatorTest {
    @Test
    public void test() {

        /**
         * [4,4,6,6,5,6,6,6,3,1,1,6,5,6,5,1,6,1,4,2,4,3,1,6,2,3,6,2,6,4]
         * 6
         * 39
         */
        missingRolls(new int[]{4,4,6,6,5,6,6,6,3,1,1,6,5,6,5,1,6,1,4,2,4,3,1,6,2,3,6,2,6,4}, 6, 39);
    }

    public int minimumMoves(String s) {
        int ans = 0;
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'X') {
                ans++;
                i = i + 3;
                if (i >= chars.length) {
                    break;
                }
            }
        }

        return ans;
    }

    public int[] missingRolls(int[] rolls, int mean, int n) {
        List<Integer> ans  = new ArrayList<>();
        long sum = (long) mean * (n + rolls.length);
        for (int roll : rolls) {
            sum -= roll;
        }

        float m = sum / n;
        if (m < 1.0 || m > 6.0) {
            return new int[0];
        }
        if (bt(ans, sum, n, (int)m + 1)) {
            return ans.stream().mapToInt(i->i).toArray();
        }
        return new int[0];
    }

    boolean bt(List<Integer> ans, long sum, int n, int u) {
        if (n == 0 && sum == 0) {
            return true;
        }
        int upper = 0;
        if (sum / n == 6) {
            upper = 6;
        } else {
            upper = (int) (sum / n) + 1;
        }
        for (int i = upper; i >= 1; i--) {
            if (sum >= i) {
                ans.add(i);
                sum -= i;
                if (bt(ans, sum, n - 1, u)) {
                    return true;
                } else {
                    sum += i;
                    ans.remove(ans.size() - 1);
                }
            }
        }
        return false;
    }

    public int[] finalPrices(int[] prices) {
        Stack<Integer> holder = new Stack();

        for (int i = 0; i < prices.length; i++) {
            while (!holder.isEmpty() && prices[holder.pop()] >= prices[i]) {
                prices[holder.pop()] -= prices[i];
                holder.pop();
            }
            holder.push(i);
        }
        return prices;
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>(k);
        int index = findX(arr, x);
        ans.add(arr[index]);
        k--;
        int l = index - 1, r = index + 1;
        while (k > 0) {
            k--;
            if (l < 0) {
                ans.add(ans.size(), arr[r++]);
            } else if (r > arr.length) {
                ans.add(0, arr[l--]);
            } else if (Math.abs(arr[l] - x) > Math.abs(arr[r] - x)) {
                ans.add(ans.size(), arr[r++]);
            } else {
                ans.add(0, arr[l--]);
            }
        }

        return ans;
    }

    public int findX(int[] arr, int x) {
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            if (l + 1 == r) {
                if (Math.abs(arr[l] - x) > Math.abs(arr[r] - x)) {
                    return r;
                } else {
                    return l;
                }
            }
            int m = (l + r) / 2;
            if (arr[m] == x) {
                return m;
            } else if (arr[m] > x) {
                r = m;
            } else {
                l = m;
            }
        }
        return l;
    }

    public List<Integer> grayCode(int n) {
        int max = (int) Math.pow(2, n);

        List<Integer> ans = new ArrayList<>(max);
        if (max <= 2) {
            for (int i = 0; i < max; i++) {
                ans.add(i);
            }
        } else {
            List<Integer> result = grayCode(n - 1);

            int pending = (int) Math.pow(2, n - 1);
            for (int i : result) {
                ans.add(i + pending);
            }
            Collections.reverse(result);
            ans.addAll(result);
        }

        return ans;
    }

}