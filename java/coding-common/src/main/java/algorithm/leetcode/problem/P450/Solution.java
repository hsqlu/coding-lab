package algorithm.leetcode.problem.P450;

import algorithm.common.TreeNode;

/**
 * 450. Delete Node in a BST
 * Medium
 * <p>
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 * <p>
 * Basically, the deletion can be divided into two stages:
 * <p>
 * Search for a node to remove.
 * If the node is found, delete the node.
 * <p>
 * Note: Time complexity should be O(height of tree).
 * <p>
 * Example:
 * <p>
 * root = [5,3,6,2,4,null,7]
 * key = 3
 * <p>
 * 5
 * / \
 * 3   6
 * / \   \
 * 2   4   7
 * <p>
 * Given key to delete is 3. So we find the node with value 3 and delete it.
 * <p>
 * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
 * <p>
 * 5
 * / \
 * 4   6
 * /     \
 * 2       7
 * <p>
 * Another valid answer is [5,2,6,null,4,null,7].
 * <p>
 * 5
 * / \
 * 2   6
 * \   \
 * 4   7
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class Solution {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }

        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }

            TreeNode temp = root.left;

            while (temp.right != null) {
                temp = temp.right;
            }

            TreeNode nr = new TreeNode(temp.val);
            nr.right = root.right;
            nr.left = deleteNode(root.left, temp.val);
            return nr;

//             if (heigh(root.left) > heigh(root.right)) {
//                 TreeNode temp = root.left;

//                 while (temp.right != null) {
//                     temp = temp.right;
//                 }

//                 TreeNode nr = new TreeNode(temp.val);
//                 nr.right = root.right;
//                 nr.left = deleteNode(root.left, temp.val);
//                 return nr;

//             } else {
//                 TreeNode temp = root.right;

//                 while (temp.left != null) {
//                     temp = temp.left;
//                 }

//                 TreeNode nr = new TreeNode(temp.val);
//                 nr.left = root.left;
//                 nr.right = deleteNode(root.right, temp.val);
//                 return nr;
//             }
        }
    }

    // int heigh(TreeNode root) {
    //     if (root == null) return 0;
    //     if (root.left == null && root.right == null)  return 1;
    //     return 1 + Math.max(heigh(root.left), heigh(root.right));
    // }
}