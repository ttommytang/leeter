public class LongestConsecutivePath {
    public static void main(String[] args) {
        TreeNode one = new TreeNode(1);
        TreeNode three = new TreeNode(3);
        TreeNode two = new TreeNode(2);
        
        three.left = one;
        three.right = two;
        
        LongestConsecutivePath test = new LongestConsecutivePath();
        int l = test.longestConsecutiveFull(three);
        
    }
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int v) {val = v;}
    }
    
    int longest;
    public int longestConsecutive(TreeNode root) {
        longest = 0;
        longestConsecutiveHelper(root);
        
        return longest;
    }
    
    public int longestConsecutiveHelper(TreeNode root) {
        if (root == null) return 0;
        
        int left = longestConsecutiveHelper(root.left);
        int right = longestConsecutiveHelper(root.right);
        
        int max = 1;
        if(root.left != null && root.left.val == root.val + 1) max = Math.max(max, 1 + left);
        if(root.right != null && root.right.val == root.val + 1) max = Math.max(max, 1 + right);
        
        longest = Math.max(max, longest);
        return max;
    }
    
    int longestFull = 0;
    
    public int longestConsecutiveFull(TreeNode root) {
        longestFull = 0;
        longestConsecutiveFullHelper(root);
        return longestFull;
    }
    
    // return int[] -> res[0] -> longest increasing path length(parent < child) ; res[1] -> decreasing
    public int[] longestConsecutiveFullHelper(TreeNode root) {
        if (root == null) return new int[2];
    
        int[] left = longestConsecutiveFullHelper(root.left);
        int[] right = longestConsecutiveFullHelper(root.right);
    
        int maxInc = 1, maxDec = 1;
    
        // Get the longest consecutive parent to child path
        if (root.left != null && root.val == root.left.val - 1)
            maxInc = Math.max(maxInc, left[0] + 1);
        if (root.right != null && root.val == root.right.val - 1)
            maxInc = Math.max(maxInc, right[0] + 1);
        if (root.left != null && root.val == root.left.val + 1)
            maxDec = Math.max(maxDec, left[1] + 1);
        if (root.right != null && root.val == root.right.val + 1)
            maxDec = Math.max(maxDec, right[1] + 1);
    
        longestFull = Math.max(longestFull, Math.max(maxInc, maxDec));
    
        if (root.left != null && root.right != null) {
            int childToChild = 0;
            if (root.val == root.left.val + 1 && root.val == root.right.val - 1)
                childToChild = left[1] + right[0] + 1;
            else if (root.val == root.left.val - 1 && root.val == root.right.val + 1)
                childToChild = left[0] + right[1] + 1;
            longestFull = Math.max(longestFull, childToChild);
        }
    
        return new int[]{maxInc, maxDec};
    }
}
