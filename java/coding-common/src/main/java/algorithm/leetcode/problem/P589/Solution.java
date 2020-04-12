package algorithm.leetcode.problem.P589;

import java.util.*;

/**
 * 589. N-ary Tree Preorder Traversal
 * Easy
 * <p>
 * Given an n-ary tree, return the preorder traversal of its nodes' values.
 * <p>
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
 * <p>
 * <p>
 * <p>
 * Follow up:
 * <p>
 * Recursive solution is trivial, could you do it iteratively?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [1,3,5,6,2,4]
 * <p>
 * Example 2:
 * <p>
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [1,2,3,6,7,11,14,4,8,12,5,9,13,10]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The height of the n-ary tree is less than or equal to 1000
 * The total number of nodes is between [0, 10^4]
 */
public class Solution {

    List<Integer> result = new LinkedList<>();

    public List<Integer> preorder(Node root) {
        if (root == null) {
            return result;
        }
        result.add(root.val);
        for (Node n : root.children) {
            preorder(n);
        }
        return result;
    }

    public List<Integer> iterative(Node root) {
        Deque<Node> holder = new LinkedList<>();
        holder.addFirst(root);
        while (null != holder.peekFirst()) {
            Node first = holder.pollFirst();
            result.add(first.val);
            if (first.children == null || first.children.isEmpty()) {
                continue;
            }
//            int l = first.children.size();

            for (int l = first.children.size(); l > 0; l--) {
                holder.addFirst(first.children.get(l - 1));
            }
        }

        return result;
    }

    public List<Integer> anotherIterative(Node root) {
        List<Integer> returnList = new ArrayList<>();
        if (root == null) {
            return returnList;
        }
        List<Node> list = new ArrayList<>();
        list.add(root);
        while (!list.isEmpty()) {
            Node node = list.remove(0);
            returnList.add(node.val);
            List<Node> children = node.children;
            List<Node> newList = new ArrayList<>(children);
            list.addAll(0, newList);
        }
        return returnList;
    }



}

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}