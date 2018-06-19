package leetcode.design;

/**
 * @date 15/06/2018.
 * @author Qiannan Lu
 */
public class BinarySearchTreeIterator {
}

class BSTIterator {
	TreeNode tree;
	TreeNode current;
	int leftHeight = 0;
	int rightHeight = 0;

	public BSTIterator(TreeNode root) {
		this.tree = root;
		TreeNode left = root;
		TreeNode right = root;

		while (left.left != null) {
			leftHeight++;
			left = root.left;
		}

		while (right.right != null) {
			rightHeight++;
			right = root.right;
		}
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return leftHeight == 0 && rightHeight == 0;
	}

	/** @return the next smallest number */
	public int next() {
		return tree.val;
	}
}