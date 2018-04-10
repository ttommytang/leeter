import java.util.*;

public class ToeplitzMatrix {
    public static void main(String[] args) {

    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        return dfsCheck(matrix, 0, matrix[0].length - 1);
    }

    private boolean dfsCheck(int[][] matrix, int row, int col) {
        int leftR = row, leftC = col - 1, rightR = row + 1, rightC = col;

        if (leftR < 0 || leftR >= matrix.length || rightR < 0 || rightR >= matrix.length || leftC < 0 || leftC >= matrix[0].length || rightC < 0 || rightC >= matrix[0].length)
            return true;

        return matrix[leftR][leftC] == matrix[rightR][rightC] && dfsCheck(matrix, leftR, leftC) && dfsCheck(matrix, rightR, rightC);
    }

    public boolean isToeplitzMatrixBFS(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        int R = matrix.length, C = matrix[0].length;

        // Using r * C + c as real index indicator
        Queue<Integer> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        queue.offer(C - 1);
        visited[0][C-1] = true;

        while(!queue.isEmpty()) {
            int diagonalLength = queue.size();
            int index = queue.peek() != null ? queue.peek() : -1;
            int diagonalValue = matrix[index / C][index % C];

            while(diagonalLength > 0) {
                int current = queue.poll();
                if (matrix[current/C][current%C] != diagonalValue) return false;
                if (current % C > 0 && !visited[(current - 1) / C][(current - 1) % C]) {
                    queue.offer(current - 1);
                    visited[(current - 1) / C][(current - 1) % C] = true;
                }
                if (current / C < R - 1 && !visited[(current + C) / C][(current + C) % C]) {
                    queue.offer(current + C);
                    visited[(current + C) / C][(current + C) % C] = true;
                }
                diagonalLength--;
            }
        }
        return true;
    }

    public boolean isToeplitzMatrixEASY(int[][] matrix) {
        for (int r = 0; r < matrix.length; ++r)
            for (int c = 0; c < matrix[0].length; ++c)
                if (r > 0 && c > 0 && matrix[r-1][c-1] != matrix[r][c])
                    return false;
        return true;
    }
}
