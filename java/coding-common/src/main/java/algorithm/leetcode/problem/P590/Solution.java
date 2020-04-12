package algorithm.leetcode.problem.P590;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    List<Integer> result = new LinkedList<>();
    public List<Integer> postorder(Node root) {
        if (root == null)
            return result;

        for (Node n : root.children) {
            postorder(n);
        }
        result.add(root.val);

        return result;
    }

    public List<Integer> iterative(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Node> list = new ArrayList<>();
        list.add(root);

        while (!list.isEmpty()) {
            Node tem = list.remove(list.size() - 1);
            result.add(0, tem.val);
            list.addAll(tem.children);
        }


        return result;
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