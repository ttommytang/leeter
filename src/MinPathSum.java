public class MinPathSum {
    public static void main(String[] args) {
    
    }
    
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        
        int[][] mem = new int[grid.length][grid[0].length];
        for (int i = 0; i < mem.length; i++) {
            for (int j = 0; j < mem[0].length; j++) {
                if (i == 0)
                    mem[i][j] = grid[i][j] + (j == 0 ? 0 : mem[i][j - 1]);
                else if (j == 0)
                    mem[i][j] = grid[i][j] + mem[i - 1][j];
                else
                    mem[i][j] = grid[i][j] + Math.min(mem[i - 1][j], mem[i][j - 1]);
            }
        }
        
        return mem[grid.length - 1][grid[0].length - 1];
    }
}
