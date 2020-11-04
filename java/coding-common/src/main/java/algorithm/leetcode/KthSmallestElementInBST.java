package algorithm.leetcode;

import algorithm.common.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Qiannan Lu
 * @date 01/06/2018.
 */
public class KthSmallestElementInBST {
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> queue = new LinkedList<>();
        List<TreeNode> index = new ArrayList<>();
        TreeNode temp = root;
        while (temp.left != null) {
            queue.addLast(temp.left);
            temp = temp.left;
        }
        queue.addLast(root);
        temp = root;
        while (temp.right != null) {
            queue.addLast(temp.right);
        }
        TreeNode node = queue.pollFirst();
        while (node != null) {
            if (node.left != null) {
                index.add(node.left);
            }
            index.add(node);
            if (node.right != null) {
                index.add(node.right);
            }
            node = queue.pollFirst();
        }

        return index.get(k - 1).val;
    }
}