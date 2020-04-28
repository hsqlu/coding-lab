package algorithm.leetcode.problem.P501;

import algorithm.common.TreeNode;

import java.util.*;

/**
 * 501. Find Mode in Binary Search Tree
 * Easy
 *
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
 *
 * Assume a BST is defined as follows:
 *
 *     The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 *     The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 *     Both the left and right subtrees must also be binary search trees.
 *
 *
 *
 * For example:
 * Given BST [1,null,2,2],
 *
 *    1
 *     \
 *      2
 *     /
 *    2
 *
 *
 *
 * return [2].
 *
 * Note: If a tree has more than one mode, you can return them in any order.
 *
 * Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
 */
public class Solution {
    Map<Integer, Integer> holder = new HashMap<>();
    int max = 0;
    List<Integer> result = new ArrayList<>();
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        traversal(root);

        for (Map.Entry<Integer, Integer> entry : holder.entrySet()) {
            if (entry.getValue() == max) {
                result.add(entry.getKey());
            }
        }

        return result.stream().mapToInt(i->i).toArray();


    }

    void traversal(TreeNode root) {
        if (root == null)
            return;
        Integer count = holder.get(root.val);
        if (count != null) {
            holder.put(root.val, count + 1);
            max = Math.max(count + 1, max);
        } else {
            holder.put(root.val, 1);
            max = Math.max(1, max);
        }
        traversal(root.left);
        traversal(root.right);
    }
}
