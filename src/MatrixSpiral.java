import java.util.*;

public class MatrixSpiral {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        spiralHelper(matrix, 0, res);

        return res;
    }

    private void spiralHelper(int[][] matrix, int offset, List<Integer> res) {
        int width  = matrix[0].length - 2 * offset;
        int height = matrix.length - 2 * offset;

        if(width <= 0 || height <= 0) return;

        // Traverse the upper rim
        for(int i = offset; i < offset + width - 1; i++)
            res.add(matrix[offset][i]);

        // Right rim
        for(int i = offset; i < offset + height - 1; i++)
            res.add(matrix[i][matrix[0].length - offset - 1]);

        // Bottom rim
        for(int i = matrix[0].length - offset - 1; i > offset; i--) {
            res.add(matrix[matrix.length - offset - 1][i]);
            if (height <= 1) break;
        }

        // Left rim
        for(int i = matrix.length - offset - 1; i > offset; i--) {
            res.add(matrix[i][offset]);
            if (width <= 1) break;
        }

        spiralHelper(matrix, offset+1, res);
    }
}
