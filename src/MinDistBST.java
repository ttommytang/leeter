import java.util.*;

public class MinDistBST {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int value) {
            val = value;
            left = null; right = null;
        }
    }

    public int minDiffInBST(TreeNode root) {
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();

        // Iteratively traverse the tree in-order
        while(root != null) {
            stack.push(root);
            root = root.left;
        }

        int minDist = Integer.MAX_VALUE;
        TreeNode prev = null;
        while(!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (prev != null) minDist = Math.min(minDist, cur.val - prev.val);
            TreeNode right = cur.right;
            while(right != null) {
                stack.push(right);
                right = right.left;
            }
            prev = cur;
        }
        return minDist;
    }

}
