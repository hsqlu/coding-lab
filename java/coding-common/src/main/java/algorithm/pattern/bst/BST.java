package algorithm.pattern.bst;

import algorithm.common.TreeNode;

public class BST {

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST(TreeNode root) {
        return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(TreeNode x, Integer min, Integer max) {
        if (x == null) return true;
        if (min != null && x.val <= min) return false;
        if (max != null && new Integer(x.val).compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.val) && isBST(x.right, x.val, max);
    }

}
