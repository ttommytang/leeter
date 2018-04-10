public class PruneTree {
    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    public TreeNode pruneTreeHelper(TreeNode root) {
        if(root == null) return null;
        
        TreeNode left = pruneTreeHelper(root.left);
        TreeNode right = pruneTreeHelper(root.right);
        
        if(left == null)  root.left = null;
        if(right == null) root.right = null;
    
        if(root.val == 1) return root;
        return left != null ? left : right;
    }
    
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(0);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(0);
        n3.left = n1; n3.right = n2;
        TreeNode n4 = new TreeNode(1);
        n4.right = n3;
        
        PruneTree test = new PruneTree();
        test.pruneTreeHelper(n4);
    }
}


