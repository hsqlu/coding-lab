package algorithm.leetcode.problem.P448;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public List<Integer> failFindDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]);

            if (nums[index - 1] > 0) {
                nums[index - 1] *= -1;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }


        return result;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();


        // This for loop will move the elements n to position (n - 1) in the array.

        // for example [4,3,2,7,8,2,3,1] will be [1, 2, 3, 4, 8, 2, 7, 8]
        for (int i = 0; i < nums.length; i++) {
            int numberToPut = nums[i];

            while (nums[numberToPut - 1] != numberToPut) {
                int temp = nums[numberToPut - 1];
                nums[numberToPut - 1] = numberToPut;
                numberToPut = temp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                result.add(i + 1);
            }
        }

        return result;
    }

    List<Integer> bad(int[] nums) {
        Set<Integer> holder = new HashSet<>();

        for (int i = nums.length; i > 0; i--) {
            holder.add(i);
        }

        for (int n : nums) {
            holder.remove(n);
        }

        List<Integer> result = new ArrayList<>();

        for (int s : holder) {
            result.add(s);
        }
        return result;
    }
}
