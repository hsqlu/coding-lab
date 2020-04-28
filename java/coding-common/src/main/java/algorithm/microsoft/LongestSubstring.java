package algorithm.microsoft;

public class LongestSubstring {

    public String solution(String input) {
        if (input.length() <= 2) {
            return input;
        }
        int maxLeft = 0, max = 0, maxRight = 0;
        int left = 0, right = 1, count = 1;
        while (right < input.length()) {
            if (input.charAt(right) == input.charAt(right - 1)) {
                count++;
                if (count == 3) {
                    if (right - 1 - left > max) {
                        max = right - 1 -left;
                        maxLeft = left;
                        maxRight = right;
                    }
                    left = right - 1;
                    count = 2;
                }
            } else {
                count = 1;
            }
            right++;
        }
        if (right - 1 - left > max) {
            max = right - 1 -left;
            maxLeft = left;
            maxRight = right;
        }
        return input.substring(maxLeft, maxRight);
    }
}
