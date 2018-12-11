package algorithm.leetcode.array;


/**
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 *
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 *
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 *
 */
public class MedianOfTwoSortedArray {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		String a = "";
		int m = nums1.length;
		int n = nums2.length;
		int[] combine = new int[m + n];
		int mid = (m + n) / 2;
		int res = (m + n) % 2;
		int i = 0, j = 0;
		while (i < m && j < n) {
			if (nums1[i] < nums2[j]) {
				combine[i + j] = nums1[i];
				i++;
			} else {
				combine[i + j] = nums2[j];
				j++;
			}
		}

		while (i < m) {
			combine[i + j] = nums1[i];
			i++;
		}

		while (j < n) {
			combine[i + j] = nums2[j];
			j++;
		}
		if (res != 0) {
			return combine[mid + 1];
		}
		return (combine[mid] + combine[mid + res]) / 2.0;
	}
}
