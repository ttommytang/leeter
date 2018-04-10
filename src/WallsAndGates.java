import java.util.*;

public class WallsAndGates {
    public static void main(String[] args) {
        int[][] grid = {{2147483647,-1,0,2147483647},{2147483647,2147483647,2147483647,-1},{2147483647,-1,2147483647,-1},{0,-1,2147483647,2147483647}};

        WallsAndGates test = new WallsAndGates();
        test.wallsAndGates(grid);
    }

    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;

        List<Coord> gates = findGates(rooms);
        for (Coord gate : gates)
            BFSupdate(rooms, gate);
    }

    private List<Coord> findGates(int[][] rooms) {
        List<Coord> gates = new ArrayList<>();
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return gates;

        for (int r = 0; r < rooms.length; r++)
            for (int c = 0; c < rooms[r].length; c++)
                if (rooms[r][c] == 0) gates.add(new Coord(r, c));

        return gates;
    }

    private int[] dirs = {-1, 0, 1, 0, -1};
    private void BFSupdate(int[][] rooms, Coord gate) {
        int R = rooms.length, C = rooms[0].length, level = 1;
        boolean[][] visited = new boolean[R][C];

        Queue<Coord> queue = new LinkedList<>();
        queue.offer(gate);
        visited[gate.r][gate.c] = true;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size > 0) {
                Coord at = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int toR = at.r + dirs[i], toC = at.c + dirs[i+1];
                    if (toR >= 0 && toR < R && toC >= 0 && toC < C && !visited[toR][toC] && rooms[toR][toC] > 0) {
                        rooms[toR][toC] = Math.min(rooms[toR][toC], level);
                        queue.offer(new Coord(toR, toC));
                        visited[toR][toC] = true;
                    }
                }
                size--;
            }
            level++;
        }
    }

    static class Coord {
        int r;
        int c;
        Coord(int row, int col) { r = row; c = col; }
    }

    public void wallsandgatesBFS(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;

        Queue<int[]> queue = new LinkedList<>();
        for (int r = 0; r < rooms.length; r++) {
            for (int c = 0; c < rooms.length; c++) {
                if (rooms[r][c] == 0)
                    queue.offer(new int[]{r,c});
            }
        }

        while(!queue.isEmpty()) {
            int[] at = queue.poll();

            for (int i = 0; i < 4; i++) {
                int toR = at[0] + dirs[i], toC = at[1] + dirs[i+1];
                if (toR < 0 || toC < 0 || toR >= rooms.length || toC >= rooms[0].length || rooms[toR][toC] != Integer.MAX_VALUE) continue;
                rooms[toR][toC] = rooms[at[0]][at[1]] + 1;
                queue.offer(new int[]{toR, toC});
            }
        }
    }




}
