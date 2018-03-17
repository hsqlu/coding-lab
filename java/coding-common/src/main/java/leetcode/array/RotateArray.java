package leetcode.array;

/**
 * Created: 2016/9/2.
 * Author: Qiannan Lu
 */
public class RotateArray {
	public static void rotate(int[] nums, int k) {
		int[] copy = nums.clone();

		for (int i = 0; i < nums.length; ++i) {
			nums[i] = copy[((7 - k) + i) % 7];
		}
	}

	public static void main(String[] args) {
		rotate(new int[]{1,2,3,4,5,6,7}, 3);
	}
}
