public class MaxIncreasingPath {
    public static void main(String[] args) {
        int[][] mat = {{9,9,4},{6,6,8},{2,1,1}};

        MaxIncreasingPath test = new MaxIncreasingPath();
        int len = test.longestIncreasingPath(mat);
    }

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int rows = matrix.length, cols = matrix[0].length;
        int[][] memo = new int[rows][cols];

        int max = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                max = Math.max(max, expand(matrix, memo, i, j));
            }
        }
        return max;

    }

    private int expand(int[][] matrix, int[][] memo, int r, int c) {
        if (memo[r][c] > 0)
            return memo[r][c];
        int max = 0;
        if (r >= 1 && matrix[r][c] < matrix[r-1][c])
            max = Math.max(max, expand(matrix, memo, r-1, c));
        if (r < matrix.length - 1 && matrix[r][c] < matrix[r+1][c])
            max = Math.max(max, expand(matrix, memo, r+1, c));
        if (c >= 1 && matrix[r][c] < matrix[r][c-1])
            max = Math.max(max, expand(matrix, memo, r, c-1));
        if (c < matrix[0].length - 1 && matrix[r][c] < matrix[r][c+1])
            max = Math.max(max, expand(matrix, memo, r, c+1));
        memo[r][c] = max + 1;
        return max + 1;
    }
}
