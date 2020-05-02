package algorithm.microsoft.array;

/**
 * Rotate Image
 * Solution
 * You are given an n x n 2D matrix representing an image.
 * <p>
 * Rotate the image by 90 degrees (clockwise).
 * <p>
 * Note:
 * <p>
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 * <p>
 * Example 1:
 * <p>
 * Given input matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * rotate the input matrix in-place such that it becomes:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * Example 2:
 * <p>
 * Given input matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * <p>
 * rotate the input matrix in-place such that it becomes:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 */
public class RotateImage {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] another = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        new RotateImage().rotate(another);
    }

    public void rotate(int[][] matrix) {
        if (2 > matrix.length) {
            return;
        }
        int n = matrix.length;

        int step = n - 1;

        // The bound of outer loop is the middle of matrix.
        // As index i is from 0, we can use less than half of matrix's length.
        // int i = 0;
        // while (i < n >> 1) {
        for (int i = 0; i < n >>1; i++) {

                /**
                 * The bound of rotate is less than the current inner matrix's
                 * side length minus 1. Since the first element of the row will be rotated to the last element of the row.
                 */
            for (int j = i; j < n - i - 1; j++) {

                int temp = matrix[i][j];

                matrix[i][j] = matrix[step - j][i];                 // Using step (length - 1) to simplify the index modification.
                matrix[step - j][i] = matrix[step - i][step - j];
                matrix[step - i][step - j] = matrix[j][step - i];
                matrix[j][step - i] = temp;
            }
            // Using for loop instead of while.
            // i++;
        }
    }
}
