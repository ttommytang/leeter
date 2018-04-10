import java.util.*;

public class SimilarTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int value) { val = value; left = null; right == null};
    }
    
    public boolean isTreeSimilar(TreeNode roo1, TreeNode root2) {
    
    }
    
    public List<Integer> orderLeaves(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
        while(!stack.isEmpty()) {
        
        }
    }
}
