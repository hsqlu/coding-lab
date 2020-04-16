package algorithm.microsoft;

/**
 * You are given a string S consisting of N letters 'a' and/or ;b;. In one move, you can swap one letter for the other (
 * 'a' for 'b' or 'b' for 'a').
 * <p>
 * Write a function solution that, given such s string S, returns the minimum number of moves required to obtain a string
 * containing no instances of three identical consecutive letters.
 * <p>
 * Examples:
 * <p>
 * S = "baaaaa" -> 1. The string without three identical consecutive letters which can be obtained in one move is "baabaa".
 * <p>
 * <p>
 * S = "baaabbaabbba" -> 2.
 * <p>
 * S = "baabab" -> 0.
 */
public class MinMoves {

    public static int solution(String s) {
        int countA = 0, countB = 0;
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a') {
                countA++;
                countB = 0;
            } else {
                countB++;
                countA = 0;
            }

            if (countA == 3) {
                countA = 0;
                count++;
            }
            if (countB == 3) {
                countB = 0;
                count++;
            }
        }

        return count;
    }

    static int secondSolution(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); ) {
            int next = i + 1;
            while (next < s.length() && s.charAt(i) == s.charAt(next)) next++;
            int distance = next - i;
            if (distance >= 3) {
                while (distance > 5) {
                    res++;
                    distance -= 3;
                }
                if (distance <= 5)
                    res++;
            }
            i = next;
        }
        return res;
    }
}
