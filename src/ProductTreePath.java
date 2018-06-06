public class ProductTreePath {
    public static void main(String[] args) {
        ProductTreePath test = new ProductTreePath();
        
        int[] x = {1,2,2};
        int[] y = {2,3,4};
        int[] d = {1,1,-1,2};
        
        int max = test.getProduct(x,y,d);
    }
    private int max;
    public int getProduct(int[] x, int[] y, int[] d) {
        // Write your code here
        if (x == null || x.length == 0 || y == null || y.length == 0 || d == null || d.length == 0) return 0;
        
        TreeNode[] nodes = new TreeNode[d.length];
        for (int i = 0; i < d.length; i++)
            nodes[i] = new TreeNode(d[i]);
        
        for (int i = 0; i < x.length; i++) {
            TreeNode parent = nodes[x[i] - 1];
            TreeNode child  = nodes[y[i] - 1];
            
            if (parent.left == null)
                parent.left = child;
            else
                parent.right = child;
        }
        
        max = 0;
        dfs(nodes[0], 1);
        return max;
    }
    
    static class TreeNode {
        int weight;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int w) {
            weight = w;
        }
    }
    
    private void dfs(TreeNode root, long cur) {
        if (root.left == null && root.right == null) {
            int prod = (int) (cur * root.weight % 1000000007);
            if (prod < 0)
                prod += 1000000007;
            max = Math.max(max, prod);
            return;
        }
        if (root.left != null)
            dfs(root.left, cur * root.weight);
        if (root.right != null)
            dfs(root.right, cur * root.weight);
    }
}
