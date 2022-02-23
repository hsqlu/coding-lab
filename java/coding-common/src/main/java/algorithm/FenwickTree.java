package algorithm;

public class FenwickTree {
    int[] sum;

    public FenwickTree(int size) {
        this.sum = new int[size + 1];
    }

    private int lowbit(int x) {
        return x & -x;
    }

    public void update(int index, int delta) {
        while (index < sum.length) {
            sum[index] += delta;
            index += lowbit(index);
        }
    }

    public int query(int index) {
        int ans = 0;
        while (index > 0) {
            ans += sum[index];
            index -= lowbit(index);
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 0, -1, 7, 5};
        FenwickTree tree = new FenwickTree(arr.length);
        for (int i = 1; i <= arr.length; i++) {
            tree.update(i, arr[i-1]);
        }

        for (int i = arr.length; i > 0; i--) {
            System.out.print(tree.query(i) + " ");
        }

        tree.update(4, 1);

        System.out.println("\nIncrease 1 on the 4th element of the array.");
        for (int i = arr.length; i > 0; i--) {
            System.out.print(tree.query(i) + " ");
        }
    }
}
