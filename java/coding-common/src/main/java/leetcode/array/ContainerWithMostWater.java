package leetcode.array;

import java.util.Arrays;

/**
 * @date 15/06/2018.
 * @author Qiannan Lu
 */
public class ContainerWithMostWater {

	public int maxArea(int[] height) {
		int l = 0, r = height.length - 1;
		int maxarea = 0;

		while (l < r) {
			maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
			if (height[l] < height[r])
				l++;
			else
				r--;
		}

		return maxarea;
	}

	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		if (nums[0] > target) {
			return nums[0] + nums[1] + nums[2];
		}
		if (nums[nums.length - 1] < target) {
			return nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1];
		}
		int j = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] >= target) {
				j = i;
			}
		}
		return 0;
		
	}

}
