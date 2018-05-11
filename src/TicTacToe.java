import java.util.Arrays;
public class TicTacToe {
    public static void main(String[] args) {
        TicTacToe test = new TicTacToe();
        
        int[] coord1 = new int[]{0, 1};
        
        if (test.addToken(coord1, 'X'))
            test.printBoard();
    
        int i = 0;
        try {
            for (; i < 10; i++) {
                test.AIMove();
                test.printBoard();
                System.out.print("\n");
            }
        } catch (TicTacToeException e) {
            e.printStackTrace();
        }
    
    }
    
    static class TicTacToeException extends Exception {
        int code;
        String message;
        TicTacToeException(int code, String msg) {
            this.code = code;
            this.message = msg;
        }
    }
    
    private char[][] mBoard;
    private int emptyCount;
    
    public TicTacToe(char[][] board) {
        mBoard = board;
        emptyCount = 0;
        // Traver the grid and count empty cells
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '-')
                    emptyCount++;
            }
        }
    }
    
    public TicTacToe() {
        mBoard = new char[3][3];
        
        for (int i = 0; i < 3; i++) {
            char[] row = new char[3];
            Arrays.fill(row, '-');
            mBoard[i] = row;
        }
        
        emptyCount = 9;
    }
    
    public boolean addToken(int[] coordinate, char token) {
        if (coordinate == null || coordinate.length != 2) return false;
        
        if (coordinate[0] < 0 || coordinate[0] >= mBoard.length || coordinate[1] < 0 || coordinate[1] > mBoard[0].length || mBoard[coordinate[0]][coordinate[1]] != '-')
            return false;
        
        mBoard[coordinate[0]][coordinate[1]] = token;
        emptyCount--;
        return true;
    }
    
    public void printBoard() {
        for (int i = 0; i < mBoard.length; i++) {
            for (int j = 0; j < mBoard[i].length; j++) {
                System.out.print(mBoard[i][j]);
                if (j < mBoard[i].length - 1)
                    System.out.print("|");
                else
                    System.out.print("\n");
            }
        }
    }
    
    public void AIMove() throws TicTacToeException{
        if (emptyCount == 0)
            try {
                throw new TicTacToeException(0, "No available cell");
            } catch (TicTacToeException e) {
                e.printStackTrace();
                throw e;
            }
    
        // Since no logic added, move to the first empty cell
        move: for (int i = 0; i < mBoard.length; i++) {
            for (int j = 0; j < mBoard[i].length; j++) {
                if (mBoard[i][j] == '-') {
                    mBoard[i][j] = 'X';
                    emptyCount--;
                    break move;
                }
            }
        }
    }
    
}
