package com.lingtoo.common.setting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created: 2016/1/5.
 * Author: Qiannan Lu
 */
public class SortDemo {
    private static int[] getRandomArray(int len) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = (int)(Math.random() * 100);
        }
        HashMap<Character, Integer>[] a = new HashMap[1];
        return arr;
    }

    public static int[][] LCS(String str1, String str2) {
        int[][] opt = new int[str2.length() + 1][str1.length() + 1];

        for (int i = 0; i <= str2.length(); i++) {
            opt[i][0] = 0;
        }

        for (int j = 0; j <= str1.length(); j++) {
            opt[0][j] = 0;
        }

        for (int j = 1; j <= str1.length(); j++) {
            for (int i = 1; i <= str2.length(); i++) {
                if (str2.charAt(i-1) == str1.charAt(j-1)) {
                    opt[i][j] = opt[i-1][j-1] + 1;
                } else {
                    opt[i][j] = ( opt[i-1][j] >= opt[i][j-1] ? opt[i-1][j] : opt[i][j-1]);
                }
            }
        }

        return opt;
    }

    public static void print(int[][] opt, String X, String Y, int i, int j) {

        if (i == 0 || j == 0) {
            return;
        }

        if (X.charAt(i - 1) == Y.charAt(j - 1)) {
            System.out.print(X.charAt(i - 1));
            print(opt, X, Y, i - 1, j - 1);  // don't put this line before the upper line. Otherwise, the order is wrong.
        }else if (opt[i - 1][j] >= opt[i][j]) {
            print(opt, X, Y, i - 1, j);
        } else {
            print(opt, X, Y, i, j - 1);}
    }

    @Test
    public void test() {
        int[][] demo = LCS("abcdef", "adefcb");
        print(demo, "abcdef", "adefcb", 6, 6);
//        int[][] demo = new int[3][3];
//        demo[0] = new int[] {1,2,3};
//        demo[1] = new int[] {0,1,2};
//        demo[2] = new int[] {0,0,1};
//        int[] array = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3 };
//        shellSort(demo);
//        clearZero(demo, 3);
}

    public int[][] clearZero(int[][] mat, int n) {
        List<Integer> rows = new ArrayList();
        List<Integer> columns = new ArrayList();

        int index = 0;
        // write code here
        for (int i = 0; i < mat.length; ++i) {
            for (int j = 0; j < mat[0].length; ++j) {
                if (mat[i][j] == 0) {
                    rows.add(i);
                    columns.add(j);
                }
            }
        }
        for (int i : rows) {
            zero(mat, i, 0);
        }
        for (int i : columns) {
            zero(mat, 0, i);
        }
        return mat;
    }
    public ListNode partition(ListNode pHead, int x) {
        // write code here
        if (null == pHead)
            return pHead;

        ListNode left = new ListNode(0);
        ListNode leftCur = left;
        ListNode right = new ListNode(0);
        ListNode rightCur = right;
        ListNode xNode = null;

        while (pHead != null) {
            if (pHead.val < x) {
                left.next = pHead;
                left = left.next;
            } else if (pHead.val > x) {
                right.next = pHead;
                right = right.next;
            } else
                xNode = pHead;
            pHead = pHead.next;
        }
        if (null != xNode) {
            left.next = xNode;
            left = left.next;
        }
        left.next = rightCur.next;

        return leftCur.next;

    }

    public void zero(int[][] mat, int m, int n) {
        if (m == 0) {
            for (int i = 0; i < mat.length; ++i) {
                mat[i][n] = 0;
            }
        }
        if (n == 0) {
            for (int i = 0; i < mat[m].length; ++i) {
                mat[m][i] = 0;
            }
        }
    }


    public void shellSort(int[] target) {
        int temp;
        long length = target.length;
        int increment = 1;
        while (increment < length/3)
            increment = increment * 3 + 1;
        int inner, outer;
        while (increment > 0) {
            for (outer = increment; outer < length; outer++) {
                temp = target[outer];
                inner = outer;
                while (inner > increment - 1 && target[inner - increment] >= temp) {
                    target[inner] = target[inner - increment];
                    inner -= increment;
                }
                target[inner] = temp;
            }
            increment = (increment - 1) / 3;
        }
    }

    public void quickSort(int[] target) {
        subQuickSort(target, 0, target.length - 1);
    }

    private void subQuickSort(int[] target, int lower, int upper) {
        if (null == target || upper - lower + 1 < 2)
            return;
        int part = partition(target, lower, upper);
        if (part == lower)
            subQuickSort(target, part + 1, upper);
        else if (part == upper)
            subQuickSort(target, lower, part - 1);
        else {
            subQuickSort(target, lower, part - 1);
            subQuickSort(target, part + 1, upper);
        }
    }

    private int partition(int[] target, int start, int end) {
        int temp = target[end];
        int index = start - 1;
        for (int i = start; i < end; ++i) {
            if (target[i] < temp) {
                index++;
                if (index != i)
                    swap(target, index, i);
            }
        }
        if ((index + 1) != end) {
            swap(target, index + 1, end);
        }
        return index + 1;
    }

    @Test
    public void sort() {
        int[] demo = new int[]{1, 8, 6, 4, 9, 7, 2, 5, 15, 71, 61, 86, 6};
        int[] array = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3 };
        quickSort(demo);
//        shellSort(demo);
    }

    private static void swap(int[] target, int source, int dest) {
        int temp = target[source];
        target[source] = target[dest];
        target[dest] = temp;
    }
}
