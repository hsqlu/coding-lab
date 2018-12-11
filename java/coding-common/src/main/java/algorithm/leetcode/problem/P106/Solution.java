package algorithm.leetcode.problem.P106;

import algorithm.leetcode.problem.TreeNode;

import java.util.Arrays;


/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * Medium
 * <p>
 * <p>
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * <p>
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * <p>
 * For example, given
 * <p>
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */
public class Solution {
    public static void main(String[] args) {
//        new Solution().buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
        new Solution().buildTree(new int[]{2, 1}, new int[]{2, 1});
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length || inorder.length < 1) {
            return null;
        }
        int l = inorder.length;
        if (l == 1) {
            return new TreeNode(inorder[0]);
        }

        int currentRoot = postorder[l - 1];
        TreeNode root = new TreeNode(currentRoot);
        int index = 0;
        while (index < l) {
            if (inorder[index] == currentRoot) {
                break;
            } else {
                index++;
            }
        }

        root.left = buildTree(Arrays.copyOfRange(inorder, 0, index),
                Arrays.copyOfRange(postorder, 0, index));
        root.right = buildTree(Arrays.copyOfRange(inorder, index + 1, l),
                Arrays.copyOfRange(postorder, index, l - 1));
        return root;

        //
//      return buildTree(inorder, 0, l - 1, postorder, 0, l - 1);
    }

    // Solution without arrays copy.
    public TreeNode buildTree(int[] inorder, int iLeft, int iRight, int[] postorder, int pLeft, int pRight) {
        printAll(inorder, iLeft, iRight);
        printAll(postorder, pLeft, pRight);
        if (iRight - iLeft < 0) {
            return null;
        }
        if (iRight == iLeft) {
            return new TreeNode(inorder[iLeft]);
        }
        TreeNode root = new TreeNode(postorder[pRight]);
        int index = 0;
        for (; index + iLeft < iRight; ++index) {
            if (inorder[index + iLeft] == postorder[pRight]) {
                break;
            }
        }
        root.left = buildTree(inorder, iLeft, index + iLeft - 1, postorder, pLeft, pLeft + index - 1);
        root.right = buildTree(inorder, iLeft + index + 1, iRight, postorder, pLeft + index, pRight - 1);
        return root;
    }

    private void printAll(int[] postorder, int pLeft, int pRight) {
        for (int i = pLeft; i <= pRight; ++i) {
            System.out.print(postorder[i] + ", ");
        }
        System.out.println();
    }
}
