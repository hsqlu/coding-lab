package algorithm;

import algorithm.common.TreeNode;

import java.util.*;

/**
 * For demo java classes which are useful in algorithm test.
 */
public class PracticeJavaUtil {

    public static void main(String[] args) {
        System.out.println(-2 + "");
        System.out.println(String.valueOf(-2));
        new PracticeJavaUtil().multiply("123", "456");
    }

    // 求最大公约数 Greatest Common Divisor
    private int gcd(int m, int n) {
        return n == 0 ? m : gcd(n, m % n);
    }
    // 辗转相减法（递归写法）
    public static int gcdSubstract(int a, int b){
        if(a == b)
            return a;
        return a > b ? gcdSubstract(a - b, b) : gcdSubstract(a, b - a);
    }

    // 辗转相减法（迭代写法）
    public static int gcd_substract_iteration(int a, int b){
        // 如果a,b不相等，则用大的数减去小的数，直到相等为止
        while(a != b){
            if(a > b)
                a = a - b;
            else
                b = b - a;
        }
        return a;
    }

    private void charUtil() {

    }

    private void stringUtil() {
        boolean isLOrD = Character.isLetterOrDigit('l');


        StringBuilder sb = new StringBuilder();
    }

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        if (num1.length() > num2.length()) {
            return multiply(num2, num1);
        }

        String s = "";
        for (int i = num1.length() - 1; i >= 0; i--) {
            s = add(s, multiply(num2, num1.charAt(i) - '0', num1.length() - 1 - i));
        }
        return s;
    }

    String multiply(String num, int c, int padding) {
        if (c == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padding; i++) {
            sb.append("0");
        }

        int carry = 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            int cur = num.charAt(i) - '0';
            int res = (cur * c + carry) % 10;
            sb.append(res);
            carry = (cur * c + carry) / 10;
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    String add(String num1, String num2) {
        if (num1.equals("")) {
            return num2;
        } else if (num2.equals("")) {
            return num1;
        }
        int i1 = num1.length() - 1, i2 = num2.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while ((i1 >= 0) || (i2 >= 0)) {
            int v1 = i1 < 0 ? 0 : num1.charAt(i1) - '0';
            int v2 = i2 < 0 ? 0 : num2.charAt(i2) - '0';
            int cur = v1 + v2 + carry;
            sb.append(cur % 10);
            carry = cur/10;
            i2--;
            i1--;
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    private void arrayUtil() {
        List<Integer> result = new ArrayList<>();
        result.stream().mapToInt(i->i).toArray();
        result.toArray(new Integer[2]);
    }

    private void collectionsUtil() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.peek();
        stack.pop();

        Integer[] input = new Integer[]{2, 4, 3, 3, 7, 8};
        Arrays.sort(input);
        Collections.binarySearch(Arrays.asList(input), 4);


        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.poll();
        queue.remove();
        queue.peek();


        Deque<Integer> deque = new LinkedList<>();
        deque.offer(1);
        deque.offerFirst(2);
        deque.offerLast(3);
        deque.poll();
        deque.pollFirst();
        deque.pollLast();
        deque.peek();
        deque.peekFirst();
        deque.peekLast();
        deque.addFirst(4);

    }

    private void mapUtil() {
        HashMap<TreeNode, TreeNode> hashMap = new HashMap<>();
    }

    private void dataStructure() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
    }
}
