import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class MagicSquare {
    public static void main(String[] args) {
        int[][] g = {{4,3,8,4}, {9,5,1,9}, {2,7,6,2}};
        MagicSquare test = new MagicSquare();
        
        int c = test.numMagicSquaresInside(g);
    }
    public int numMagicSquaresInside(int[][] grid) {
        if (grid == null || grid.length < 3 || grid[0].length < 3) return 0;
        
        Set<Integer> nums = new HashSet<>();
        nums.addAll(Arrays.asList(1,2,3,4,5,6,7,8,9));
        
        int R = grid.length, C = grid[0].length, count = 0;
        for (int i = 2; i < R; i++) {
            for (int j = 2; j < C; j++) {
                if (check(grid, i, j, new HashSet<Integer>(nums)))
                    count++;
            }
        }
        return count;
        
    }
    
    private boolean check(int[][] grid, int r, int c, Set<Integer> nums) {
        int diaSum = grid[r][c] + grid[r-1][c-1] + grid[r-2][c-2];
        int inDiaSum = grid[r-2][c] + grid[r-1][c-1] + grid[r][c-2];
        if (diaSum != inDiaSum) return false;
        
        for (int i = r-2; i <= r; i++) {
            int temp = 0;
            for (int j = c-2; j <= c; j++) {
                if (!nums.contains(grid[i][j]))
                    return false;
                temp += grid[i][j];
                nums.remove(grid[i][j]);
            }
            if (diaSum != temp)
                return false;
        }
        
        for (int j = c-2; j <= c; j++) {
            int temp = 0;
            for (int i = r-2; i <= r; i++) {
                temp += grid[i][j];
            }
            if (diaSum != temp)
                return false;
        }
        return true;
    }
}
