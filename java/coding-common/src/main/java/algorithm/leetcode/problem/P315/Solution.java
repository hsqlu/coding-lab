package algorithm.leetcode.problem.P315;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 315. Count of Smaller Numbers After Self
 * Hard
 *
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 *
 * Example 2:
 *
 * Input: nums = [-1]
 * Output: [0]
 *
 * Example 3:
 *
 * Input: nums = [-1,-1]
 * Output: [0,0]
 *
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 105
 *     -104 <= nums[i] <= 104
 */
public class Solution {
    class Item {
        private int val;
        private int index;

        public Item(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(i, nums[i]);
        }

        mergeSort(items, 0, n-1, ans);
        return Arrays.stream(ans).boxed().collect(Collectors.toList());
    }

    private void mergeSort(Item[] items, int l, int h, int[] ans) {
        if (l >= h) {
            return;
        }
        int m = (l + h)/2;
        mergeSort(items, l, m, ans);
        mergeSort(items, m+1, h, ans);
        merge(items, l, m, m+1, h, ans);
    }

    private void merge(Item[] items, int l, int lE, int h, int hE, int[] ans) {
        Item[] temp = new Item[hE - l + 1];

        int lIndex = l;
        int hIndex = h;
        int counter = 0, index = 0;
        while (lIndex <= lE && hIndex <= hE) {
            if (items[lIndex].val > items[hIndex].val) {
                temp[index++] = items[hIndex++];
                counter++;
            } else {
                ans[items[lIndex].index] += counter;
                temp[index++] = items[lIndex++];
            }
        }
        while (hIndex <= hE) {
            temp[index++] = items[hIndex++];
            counter++;
        }

        while (lIndex <= lE) {
            ans[items[lIndex].index] += counter;
            temp[index++] = items[lIndex++];
        }

        System.arraycopy(temp, 0, items, l, temp.length);
    }


    public List<Integer> countSmaller2(int[] nums) {
//        List<Integer> ans = new LinkedList<>();

        List<Integer> set = Arrays.stream(nums).boxed().distinct().sorted().collect(Collectors.toList());
//        Map<Integer, Integer> cache = Arrays.stream(nums).boxed().distinct().sorted().collect(HashMap::new, (map, ch) -> map.put(ch, map.size()), Map::putAll);


        Map<Integer, Integer> cache = new HashMap<>();
        int index = 1;
        while (!set.isEmpty()) {
            System.out.println(set.get(0) + ": " + index);
            cache.put(set.remove(0), index++);
        }
        int[] ans = new int[nums.length];

        BIT bitTree = new BIT(cache.size());

        for (int i = nums.length - 1; i >= 0; i--) {
            int val = cache.get(nums[i]);
            ans[i] = bitTree.query(val);
//            ans.add(0, bitTree.query(val));
            bitTree.update(val + 1, 1);
        }

//        ans.forEach(System.out::println);
        return Arrays.stream(ans).boxed().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        new Solution().countSmaller2(new int[]{5,2,6,1});
    }
    class BIT {
        int size;
        int[] sum;
        public BIT(int n) {
            this.size = n;
            this.sum = new int[n + 1];
        }

        private int lowbit(int x) {
            return x & -x;
        }
        public int query(int index) {
            int ans = 0;
            while (index > 0) {
                ans += sum[index];
                index -= lowbit(index);
            }
            return ans;
        }

        public void update(int index, int delta) {
            while (index < sum.length) {
                sum[index] += delta;
                index += lowbit(index);
            }
        }
    }
}
