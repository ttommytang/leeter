public class ImageOverlap {
    public static void main(String[] args) {
        int[][] A = {{1,1,0},{0,1,0},{0,1,0}};
        int[][] B = {{0,0,0},{0,1,1},{0,0,1}};
        
        ImageOverlap test = new ImageOverlap();
        int o = test.largestOverlap(A, B);
    }
    
    public int largestOverlap(int[][] A, int[][] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0 || A[0].length == 0 || B[0].length == 0) return 0;
        
        int Dr = A.length, Dc = A[0].length;
        int max = 0;
        for (int dr = -Dr + 1; dr < Dr; dr++) {
            for (int dc = -Dc + 1; dc < Dc; dc++) {
                // Calculate the overlap with transform by shift r by dr and c by dc
                int overlap = 0;
                for (int r = 0; r < B.length; r++) {
                    for (int c = 0; c < B[r].length; c++) {
                        if (r + dr < 0 || r + dr >= Dr || c + dc < 0 || c + dc >= Dc) continue;
                        overlap += (B[r][c] + A[r + dr][c + dc])/2;
                    }
                }
                max = Math.max(max, overlap);
            }
        }
        return max;
    }
}
