package algorithm.microsoft.array;

/**
 * Trapping Rain Water
 * Solution
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 *
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 *
 * Example:
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */
public class TrappingRainWater {

    public int dpTrap(int[] height) {
        int ans = 0;

        if (null == height || height.length < 3) {
            return ans;
        }
        int l = height.length;

        int[] maxL = new int[l];
        int maximumL = 0;
        for (int i = 0; i < l; i++) {
            maximumL = Math.max(maximumL, height[i]);
            maxL[i] = maximumL;
        }
        int[] maxR = new int[l];
        int maximumR = 0;
        for (int i = l - 1; i >= 0; i--) {
            maximumR = Math.max(maximumR, height[i]);
            maxR[i] = maximumR;
        }

        for (int i = 0; i < l; i++) {
            int min = Math.min(maxL[i], maxR[i]);
            ans += min < height[i] ? 0 : min - height[i];
        }

        return ans;
    }

    // Two points approach
    public int trap(int[] height) {
        int ans = 0;

        if (null == height || height.length < 3) {
            return ans;
        }

        int l = 0, r = height.length - 1;

        int maxL = height[l], maxR = height[r];

        // Remember l <= r to update the last middle position!!!
        while (l <= r) {
            if (height[l] >= maxL) {
                maxL = height[l];
                l++;
                continue;
            }
            if (height[r] >= maxR) {    // Remember using the elevation value <values in array instead of index!!!> to compare with the maximum.
                maxR = height[r];
                r--;
                continue;
            }
            if (maxL <= maxR) {
                ans += maxL - height[l];
                l++;
            } else {
                ans += maxR - height[r];
                r--;
            }
        }
        return ans;
    }

    // Two points clearer way?
    public int easyWayTwoPoints(int[] height) {
        int ans = 0;
        if (null == height || height.length < 3) {
            return ans;
        }
        int l = 0, r = height.length - 1;

        int leftMax = 0, rightMax = 0;
        while (l < r) {
            if (height[l] < height[r]) {
                if (height[l] >= leftMax)
                    leftMax = height[l];
                else
                    ans += (leftMax - height[l]);
                ++l;
            }
            else {
                if (height[r] >= rightMax)
                    rightMax = height[r];
                else
                    ans += (rightMax - height[r]);
                --r;
            }
        }
        return ans;
    }
}
