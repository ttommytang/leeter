import java.util.*;
import java.util.LinkedList;

public class PoliceDistance {
    public static void main(String[] args) {
        int[][] arr = {{0,-1,0},{0,1,1},{0,0,0}};
        
        PoliceDistance test = new PoliceDistance();
        int[][] res = test.policeDistance(arr);
    }
    
    public int[][] policeDistance(int[][] matrix) {
        // Write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return matrix;
        
        int R = matrix.length, C = matrix[0].length;
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (matrix[i][j] == 1) q.offer(i * C + j);
                else if (matrix[i][j] == 0) matrix[i][j] = Integer.MAX_VALUE;
            }
        }
        
        int distance = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        while(!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                matrix[cur / C][cur % C] = Math.min(distance, matrix[cur / C][cur % C]);
                
                for (int d = 0; d < 4; d++) {
                    int nR = cur/C + dirs[d], nC = cur%C + dirs[d+1];
                    if (nR >= 0 && nR < R && nC >= 0 && nC < C && matrix[nR][nC] == Integer.MAX_VALUE)
                        q.offer(nR * C + nC);
                }
            }
            distance++;
        }
        return matrix;
    }
    
    
}
