import java.util.*;
import java.util.LinkedList;

class ConnectIslands {
	public static void main(String[] args) {
		ConnectIslands test = new ConnectIslands();
		int[][] mat = {{1,0,1}, {0,1,1}, {0,1,0}};
		
		int res = test.largestIsland(mat);

	}

	private int[] dirs = {-1, 0, 1, 0, -1};

	public void explore(int[][] grid, Map<Integer, Integer> cellMap, Map<Integer, Integer> islandMap, int r, int c, int id) {
		int R = grid.length, C = grid[0].length;
		int idx = r * C + c;

		islandMap.put(id, islandMap.getOrDefault(id, 0) + 1);
		cellMap.put(idx, id);

		for (int d = 0; d < 4; d++) {
			int nR = r + dirs[d], nC = c + dirs[d+1];
			if (nR >= 0 && nR < R && nC >= 0 && nC < C && grid[nR][nC] == 1 && !cellMap.containsKey(nR * grid[0].length + nC)) {
				explore(grid, cellMap, islandMap, nR, nC, id);
			}
		}
	}

	public int largestIsland(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

		int R = grid.length, C = grid[0].length;
		Map<Integer, Integer> cellToIslands = new HashMap<>();
		Map<Integer, Integer> islandSize = new HashMap<>();
		List<Integer> zeros = new LinkedList<>();

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int idx = i * C + j;
				if (grid[i][j] == 0) {
					zeros.add(idx);	
					continue;
				} 

				if (cellToIslands.containsKey(idx)) continue;

				explore(grid, cellToIslands, islandSize, i, j, idx);
			}
		}

		if (islandSize.size() == 0) return 1;
		if (islandSize.size() == 1) return islandSize.values().iterator().next();

		int max = 0;
		for (int zero : zeros) {
		 	int zeroR = zero/C, zeroC = zero%C;

		 	Set<Integer> connected = new HashSet<>();
		 	int unionSize = 1;

		 	for (int d = 0; d < 4; d++) {
		 		int nR = zeroR + dirs[d], nC = zeroC + dirs[d+1], nIdx = nR * C + nC;
		 		if (nR >= 0 && nR < R && nC >= 0 && nC < C && grid[nR][nC] == 1 && !connected.contains(cellToIslands.get(nIdx))) {
		 			connected.add(cellToIslands.get(nIdx));
		 			unionSize += islandSize.get(cellToIslands.get(nIdx));
		 		}
		 	}

		 	max = Math.max(max, unionSize);
		} 

		return max;
	}
}