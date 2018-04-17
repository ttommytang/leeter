import java.util.*;
import java.util.LinkedList;

public class SlidingPuzzle {
    public static void main(String[] args) {
        SlidingPuzzle test = new SlidingPuzzle();
        
        int[][] board1 = {{1,2,3}, {4,5,6}, {7,0,8}};
        String res = test.jigsawPuzzle(board1);
    }
    
    private int[] dirs = new int[]{0, -1, 0, 1, 0};
    /**
     * @param matrix: The 3*3 matrix
     * @return: The answer
     */
    public String  jigsawPuzzle(int[][] matrix) {
        // Write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return "NO";
        
        // Find the start coordinate and the missing number by marking an auxiliary array of size 9
        int initRow = 0, initCol = 0, R = matrix.length, C = matrix[0].length;
        int[] mark = new int[9];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (matrix[i][j] == 0) {
                    initRow = i;
                    initCol = j;
                } else {
                    mark[matrix[i][j]-1]--;
                }
            }
        }
        
        // The string representation of the matrix should be like:
        StringBuilder ideal = new StringBuilder();
        for (int i = 1; i <= 9; i++) {
            if (mark[i-1] == 0) ideal.append('0');
            else ideal.append(i);
        }
        String targetRep = ideal.toString();
        
        Set<String> visited = new HashSet<>();
        Queue<BoardNode> queue = new LinkedList<>();
        
        BoardNode node = new BoardNode(matrix, initRow, initCol);
        if (node.represent.equals(targetRep)) return "YES";
        queue.offer(node);
        visited.add(node.represent);
        
        while(!queue.isEmpty()) {
            BoardNode cur = queue.poll();
            if (cur == null) continue;
            
            int fromRow = cur.nullRow, fromCol = cur.nullCol, fromIdxRep = fromRow * C + fromCol;
            for (int i = 0; i < 4; i++) {
                int toRow = fromRow + dirs[i], toCol = fromCol + dirs[i+1];
                
                if (toRow >= 0 && toRow < R && toCol >= 0 && toCol < C) {
                    // Before we actually construct a new node and add into the queue, use string swap to quick check if it's visited
                    int toIdxRep = toRow * C + toCol;
                    String toRep = swap(cur.represent, fromIdxRep,toIdxRep);
                    
                    if (visited.contains(toRep)) continue;
                    // Check before add to save more time
                    if (toRep.equals(targetRep)) return "YES";
                    visited.add(toRep);
                    
                    // Clone then swap the '0'
                    int[][] newMat = matrixClone(cur.matrix);
                    int temp = newMat[fromRow][fromCol];
                    newMat[fromRow][fromCol] = newMat[toRow][toCol];
                    newMat[toRow][toCol] = temp;
                    BoardNode neighbor = new BoardNode(newMat, toRow, toCol, toRep);
                    queue.offer(neighbor);
                }
            }
        }
        return "NO";
        
    }
    
    private int[][] matrixClone(int[][] matrix) {
        int[][] res = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            res[i] = matrix[i].clone();
        }
        return res;
    }
    
    private String swap(String str, int left, int right) {
        char[] chStr = str.toCharArray();
        char temp = chStr[left];
        chStr[left] = chStr[right];
        chStr[right] = temp;
        return new String(chStr);
    }
    
    static class BoardNode {
        int[][] matrix;
        int nullRow;
        int nullCol;
        String represent;
        BoardNode(int[][] mat, int r, int c) {
            matrix = mat;
            nullRow = r;
            nullCol = c;
            
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    temp.append(matrix[i][j]);
                }
            }
            represent = temp.toString();
        }
    
        BoardNode(int[][] mat, int r, int c, String rep) {
            matrix = mat;
            nullRow = r;
            nullCol = c;
            represent = rep;
        }
    }
}
