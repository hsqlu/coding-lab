package leetcode.array;

/**
 * Given an array of integers, find two numbers such that they add up to a specific target
 * The function twoSum should return indices of the two numbers such that they add up to the target,
 * where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution.
 *
 * Input: numbers={2, 7, 11, 15}, target=9
 *
 * Output: index1=1, index2=2
 */

import java.util.HashMap;
import java.util.Map;

/**
 * TLE
 * Time complexity in worst case: O(n^2).
 */
public class TwoSum {

	public int[] twoSum(int[] numbers, int target) {
		int[] ret = new int[2];
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] <= target) {
				for (int j = i + 1; j < numbers.length; j++) {
					if (numbers[i] + numbers[j] == target) {
						ret[0] = i + 1;
						ret[1] = j + 1;
					}
				}
			}
		}
		return ret;
	}

	/**
	 * 数组内数字不能重复  重复就出错了
	 * @param numbers
	 * @param target
	 * @return
	 */
	public static int[] twoSumUseMap(int[] numbers, int target) {
		int[] res = new int[2];

		Map<Integer, Integer> numMap = new HashMap<>();

		for (int i = 0; i < numbers.length; ++i) {
			numMap.put(numbers[i], i);
		}

		for (int m : numMap.keySet()) {
			if (numMap.get(target - m) != null) {
				res[0] = numMap.get(m);
				res[1] = numMap.get(target - m);
				break;
			}
		}

		return res;
	}
	/**
	 *
	 * 如果输入数组是有序的
	 *
	 * @param numbers
	 * @param target
	 * @return Indices
	 */
	public int[] twoSumForSortedArray(int[] numbers, int target) {
		int[] ret = new int[2];
		int j = numbers.length-1;
		for (int i = 0; i < numbers.length; i++) {
			while (numbers[i] + numbers[j] > target) {
				j--;
			}
			if (numbers[i] + numbers[j] == target) {
				ret[0] = i;
				ret[1] = j;
				return ret;
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		twoSumUseMap(new int[] {2, 7, 11, 15}, 9);
	}
}