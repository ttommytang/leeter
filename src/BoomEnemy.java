public class BoomEnemy {
    public static void main(String[] args) {
        char[][] grid = {{},{}};
    }
    
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        
        int R = grid.length;
        int C = grid[0].length;
        
        int[][] left = new int[R][C];
        int[][] right = new int[R][C];
        int[][] top = new int[R][C];
        int[][] bottom = new int[R][C];
        
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (j == 0) {
                    left[i][j] = grid[i][j] == 'E' ? 1 : 0;
                    right[i][C-j-1] = grid[i][C-j-1] == 'E' ? 1 : 0;
                    continue;
                }
                left[i][j] = grid[i][j] == 'W' ? 0 : grid[i][j] == 'E' ? 1 + left[i][j-1] : left[i][j-1];
                right[i][C-j-1] = grid[i][C-j-1] == 'W' ? 0 : grid[i][C-j-1] == 'E' ? 1 + right[i][C-j] : right[i][C-j];
            }
        }
        
        for (int j = 0; j < C; j++) {
            for (int i = 0; i < R; i++) {
                if (i == 0) {
                    top[i][j] = grid[i][j] == 'E' ? 1 : 0;
                    bottom[R-i-1][j] = grid[R-i-1][j] == 'E' ? 1 : 0;
                    continue;
                }
                top[i][j] = grid[i][j] == 'W' ? 0 : grid[i][j] == 'E' ? 1 + top[i-1][j] : top[i-1][j];
                bottom[R-i-1][j] = grid[R-i-1][j] == 'W' ? 0 : grid[R-i-1][j] == 'E' ? 1 + bottom[R-i][j] : bottom[R-i][j];
            }
        }
        
        int max = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == '0') {
                    max = Math.max(max, top[i][j] + bottom[i][j] + left[i][j] + right[i][j]);
                }
            }
        }
        return max;
    }
}
