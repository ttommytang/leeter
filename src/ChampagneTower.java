public class ChampagneTower {
    public double champagneTower(int poured, int query_row, int query_glass) {
        if(query_glass > query_row) return 0;
        
        double[][] mem = new double[query_row + 1][query_row + 1];
        
        mem[0][0] = 1.0 * poured;
        
        for (int row = 0; row < query_row; row++) {
            for(int col = 0; col <= row; col++) {
                if(mem[row][col] > 1.0) {
                    double extra = mem[row][col] - 1.0;
                    mem[row][col] = 1.0;
                    
                    mem[row + 1][col] += 0.5 * extra;
                    mem[row + 1][col + 1] += 0.5 * extra;
                }
            }
        }
        
        return mem[query_row][query_glass] > 1.0 ? 1.0 : mem[query_row][query_glass];
    }
    
    //    public double champagneTower(int poured, int query_row, int query_glass) {
//        double[][] matrix = new double[query_row][query_glass];
//
//        for (int i = 1; i <= poured; i++) {
//            updateMatrix(matrix, 0, 0, 1.0);
//        }
//
//        return matrix[query_row][query_glass];
//    }
//
//    private void updateMatrix(double[][] matrix, int row, int col, double volume) {
//        if (row >= matrix.length || col >= matrix[0].length) return;
//
//        double extra = (matrix[row][col] + volume) - 1.0;
//
//        if (extra > 0) {
//            matrix[row][col] = 1.0;
//            updateMatrix(matrix, row + 1, col, 0.5*extra);
//            updateMatrix(matrix, row + 1, col + 1, 0.5*extra);
//
//        } else {
//            matrix[row][col] = matrix[row][col] + volume;
//        }
//    }
}
