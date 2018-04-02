import java.util.*;

public class HitAndFall {
    public static void main(String[] args) {
        int[][] wall1 = {{1,0,0,0}, {1,1,1,0}};
        int[][] wall2 = {{1,0,0,0}, {1,1,0,0}};
        
        int[][] hits1 = {{1,3}, {1,1}, {0,0}, {1,0}};
        int[][] hits2 = {{1,1}, {1,0}};
        
        HitAndFall test = new HitAndFall();
        
        int[] falls1 = test.hitBricks(wall1, hits1);
        int[] falls2 = test.hitBricks(wall2, hits2);
    }
    
    private int[] dirs = new int[]{0, -1, 0, 1, 0};
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int[] res = new int[hits.length];
        int rows = grid.length, cols = grid[0].length;
        
        int[][] A = new int[rows][cols];
        for (int r = 0; r < rows; r++)
            A[r] = grid[r].clone();
        
        for (int[] hit : hits)
            A[hit[0]][hit[1]] = 0;
        
        // DSU need to be size of R*C + 1, last union for roof
        DSU dsu = new DSU(rows * cols + 1);
        
        // Build DSU based on post-hit grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (A[r][c] == 1) {
                    int idx = r * cols + c;
                    if (r == 0)
                        dsu.union(idx, rows * cols);
                    if (r > 0 && A[r - 1][c] == 1)
                        dsu.union(idx, (r - 1)*cols + c);
                    if (c > 0 && A[r][c - 1] == 1)
                        dsu.union(idx, r * cols + c - 1);
                }
            }
        }
        
        int t = hits.length - 1;
        while(t >= 0) {
            int hitR = hits[t][0];
            int hitC = hits[t][1];
            
            int postRoofUnionSize = dsu.roofUnionSize();
            if (grid[hitR][hitC] == 1) {
                int hitIndex = hitR * cols + hitC;
                for (int i = 0; i < 4; i++) {
                    int exploreR = hitR + dirs[i];
                    int exploreC = hitC + dirs[i+1];
                    
                    if (exploreR >= 0 && exploreR < rows && exploreC >= 0 && exploreC < cols && A[exploreR][exploreC] == 1)
                        dsu.union(hitIndex, exploreR * cols + exploreC);
                }
                if (hitR == 0)
                    dsu.union(hitIndex, rows * cols);
                A[hitR][hitC] = 1;
                res[t] = Math.max(dsu.roofUnionSize() - postRoofUnionSize - 1, 0);
            }
            t--;
        }
        return res;
    }
    
    static class DSU {
        int[] parents;
        int[] rank;
        int[] sz;
        
        DSU(int N) {
            parents = new int[N];
            for (int i = 0; i < N; i++)
                parents[i] = i;
            rank = new int[N];
            sz = new int[N];
            Arrays.fill(sz, 1);
        }
        
        int find(int x) {
            if (x == parents[x]) return x;
            return parents[x] = find(parents[x]);
        }
        
        void union(int x, int y) {
            int xParent = find(x);
            int yParent = find(y);
            
            if (xParent == yParent) return;
            if (rank[xParent] < rank[yParent]) {
                int temp = yParent;
                yParent = xParent;
                xParent = temp;
            }
            if (rank[xParent] == rank[yParent]) rank[xParent]++;
            
            parents[yParent] = xParent;
            sz[xParent] += sz[yParent];
        }
        
        int unionSize(int x) {
            return sz[find(x)];
        }
        
        int roofUnionSize() {
            return unionSize(sz.length - 1) - 1;
        }
    }
}
