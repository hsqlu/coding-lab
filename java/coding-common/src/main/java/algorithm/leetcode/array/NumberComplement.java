package algorithm.leetcode.array;

/**
 * @author Qiannan Lu
 * @date 01/06/2018.
 */
public class NumberComplement {
    public int findComplement(int num) {
        String s = Integer.toBinaryString(num);
        int t = (int) (Math.pow(2, s.length()) - 1);
        return t - num;
    }

    public static void main(String[] args) {
        new NumberComplement().findComplement(5);
    }
}
