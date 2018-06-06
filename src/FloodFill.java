import java.util.LinkedList;
import java.util.Queue;

public class FloodFill {
    public static void main(String[] args) {
        int[][] g = {{0, 1, 1},{0, 0, 1},{0, 0, 0}};
        
        FloodFill test = new FloodFill(g);
        test.fill(0,0);
    }
    // grid represent the bitmap, filled with either 0 or 1
    private int[][] grid;
    private int R;
    private int C;
    private int[] dirs = {-1, 0, 1, 0, -1};
    
    public FloodFill(int[][] g) {
        // Assume input grid is valid
        grid = g;
        R = g.length;
        C = g[0].length;
    }
    
    public void fill(int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c > grid[0].length)
            return;
        
        int origin = grid[r][c];
        int toFill = origin == 0 ? 1 : 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(C * r + c);
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            int curR = cur / C, curC = cur % C;
            grid[curR][curC] = toFill;
            
            for (int i = 0; i < 4; i++) {
                int nextR = curR + dirs[i];
                int nextC = curC + dirs[i+1];
                
                if (nextR >= 0 && nextR < R && nextC >= 0 && nextC < C && grid[nextR][nextC] == origin)
                    q.offer(nextR * C + nextC);
            }
        }
    }
}
