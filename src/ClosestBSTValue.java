public class ClosestBSTValue {
    public static void main(String[] args) {
    
    }
    
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    
    public int closestValue(TreeNode root, double target) {
        // write your code here
        if (root == null) return 0;
        double leftDiff = Double.MAX_VALUE, rightDiff = Double.MAX_VALUE, rootDiff = Math.abs(1.0 * root.val - target);
        
        if (root.val > target) {
            int left = root.val;
            if (root.left != null) {
                left = closestValue(root.left, target);
                leftDiff = Math.abs(1.0 * left - target);
            }
            if (root.left != null && leftDiff < rootDiff) return left;
            else return root.val;
        } else if (root.val == target)
            return root.val;
        else {
            int right = root.val;
            if (root.right != null) {
                right = closestValue(root.right, target);
                rightDiff = Math.abs(1.0 * right - target);
            }
            if (root.right != null && rightDiff < rootDiff) return right;
            else return root.val;
        }
    }
}
