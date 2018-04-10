import java.util.*;

public class BestMeetingPoint {
    public static void main(String[] args) {

    }

    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        List<Integer> rows = new ArrayList<>(), cols = new ArrayList<>();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == 1) {
                    rows.add(r);
                    cols.add(c);
                }
            }
        }

        Collections.sort(rows);
        Collections.sort(cols);

        int meetingR = rows.get((rows.size() + 1)/2 - 1);
        int meetingC = cols.get((cols.size() + 1)/2 - 1);

        int distance = 0;
        for (int i : rows) distance += Math.abs(i - meetingR);
        for (int j : cols) distance += Math.abs(j - meetingC);

        return distance;
    }
}
