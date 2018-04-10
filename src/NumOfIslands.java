public class NumOfIslands {
    public static void main(String[] args) {
        NumOfIslands test = new NumOfIslands();
        
        boolean[][] grid = {};
    }
    static class DSU {
        int[] parents;
        int[] rank;
        int count;
        
        public DSU(int len) {
            parents = new int[len];
            for (int i = 0; i < len; i++)
                parents[i] = i;
            rank = new int[len];
            count = 0;
        }
        
        int find(int x) {
            if (parents[x] == x) return x;
            return parents[x] = find(parents[x]);
        }
        
        void union(int x, int y) {
            int xParent = find(x), yParent = find(y);
            
            if (xParent == yParent) return;
            
            if (rank[yParent] > rank[xParent]) {
                int temp = yParent;
                yParent = xParent;
                xParent = temp;
            }
            
            if (rank[xParent] == 0 && rank[yParent] == 0) rank[xParent]++;
            parents[yParent] = xParent;
        }
    }
    
    /**
     * @param grid: a boolean 2D matrix
     * @return: an integer
     */
    public int numIslands(boolean[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int rows = grid.length, cols = grid[0].length;
        
        DSU dsu = new DSU(rows * cols);
        int[] dirs = {-1,0,1,0,-1};
        int singulars = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j]) {
                    int expand = 0;
                    for (int d = 0; d < 4; d++) {
                        int r = i + dirs[d], c = j + dirs[d+1];
                        if (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c]) {
                            expand++;
                            dsu.union(i * cols + j, r * cols + c);
                        }
                    }
                    
                    if (expand == 0) singulars++;
                }
            }
            
        }
        return dsu.count + singulars;
    }
}
