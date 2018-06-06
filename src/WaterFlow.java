import java.util.ArrayList;
import java.util.List;

public class WaterFlow {
    public static void main(String[] args) {
        int[][] m = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        WaterFlow wf = new WaterFlow();

        List<int[]> res = wf.pacificAtlantic(m);
    }

    private int[][] flowable;
    private int[][] map;
    private int rows;
    private int cols;

    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return res;

        rows = matrix.length;
        cols = matrix[0].length;
        flowable = new int[rows][cols];
        map = matrix;

        // Mark the edge of pacific and atlantic
        for (int i = 0; i < rows; i++) {
            expandPacific(i, 0);
            expandAtlantic(i, cols-1);
        }

        for (int j = 0; j < cols; j++) {
            expandPacific(0, j);
            expandAtlantic(rows-1, j);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (flowable[i][j] == 11) {
                    int[] coord = new int[2];
                    coord[0] = i;
                    coord[1] = j;
                    res.add(coord);
                }
            }
        }

        return res;
    }

    private void expandPacific(int r, int c) {
        if (flowable[r][c] % 10 == 0)
            flowable[r][c] += 1;
        else
            return;

        if (c >= 1 && map[r][c-1] >= map[r][c] && flowable[r][c-1] % 10 == 0)
            expandPacific(r, c-1);
        if (c < cols - 1 && map[r][c+1] >= map[r][c] && flowable[r][c+1] % 10 == 0)
            expandPacific(r, c+1);
        if (r >= 1 && map[r-1][c] >= map[r][c] && flowable[r-1][c] % 10 == 0)
            expandPacific(r-1, c);
        if (r < rows - 1 && map[r+1][c] >= map[r][c] && flowable[r+1][c] % 10 == 0)
            expandPacific(r+1, c);
    }

    private void expandAtlantic(int r, int c) {
        if (flowable[r][c] / 10 == 0)
            flowable[r][c] += 10;
        else
            return;

        if (c >= 1 && map[r][c-1] >= map[r][c] && flowable[r][c-1] / 10 == 0)
            expandAtlantic(r, c-1);
        if (c < cols - 1 && map[r][c+1] >= map[r][c] && flowable[r][c+1] / 10 == 0)
            expandAtlantic(r, c+1);
        if (r >= 1 && map[r-1][c] >= map[r][c] && flowable[r-1][c] / 10 == 0)
            expandAtlantic(r-1, c);
        if (r < rows - 1 && map[r+1][c] >= map[r][c] && flowable[r+1][c] / 10 == 0)
            expandAtlantic(r+1, c);
    }
}
