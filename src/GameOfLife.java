public class GameOfLife {
    public static void main(String[] args) {
        GameOfLife test = new GameOfLife();
        int[][] board = {{1,1}, {1,0}};
        test.gameOfLife(board);
    }
    
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        
        int[] cDirs = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] rDirs = {0, 1, 1, 1, 0, -1, -1, -1};
        int rows = board.length, cols = board[0].length;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int liveNeighbors = 0;
                for (int n = 0; n < 8; n++) {
                    int row = i + rDirs[n], col = j + cDirs[n];
                    if (row >= 0 && row < rows && col >= 0 && col < cols)
                        liveNeighbors += (board[row][col])%10;
                }
                
                // Mark the status of next generation as 10* digit -> only update if next generation will be live
                if (board[i][j] == 1 && (liveNeighbors == 2 || liveNeighbors == 3)) board[i][j] += 10;
                if (board[i][j] == 0 && liveNeighbors == 3) board[i][j] += 10;
            }
        }
        
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                board[i][j] /= 10;
    }
}
