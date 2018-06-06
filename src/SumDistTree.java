import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class SumDistTree {
    private int[] subTreeSize;
    private int[] subTreeSum;
    private int N;
    private Map<Integer, List<Integer>> adj;
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        if (edges == null || edges.length == 0 || N < 1) return new int[0];
        
        // Build adjacent list
        adj = new HashMap<>();
        for (int i = 0; i < N; i++) {
            adj.put(i, new ArrayList<Integer>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        
        this.N = N;
        this.subTreeSize = new int[N];
        Arrays.fill(subTreeSize, 1);
        this.subTreeSum = new int[N];
        
        // pick 0 as the dummy root, process the subtree size and path sums
        preprocess(0, new HashSet<Integer>());
        
        int[] res = new int[N];
        res[0] = subTreeSum[0];
        postprocess(0, res, new HashSet<Integer>());
        return res;
    }
    
    private void preprocess(int root, Set<Integer> visited) {
        visited.add(root);
        for (int child : adj.get(root)) {
            if (visited.contains(child)) continue;
            
            // Pre process the child first, cause we need the result from child before we can calculate the parent
            preprocess(child, visited);
            
            subTreeSize[root] += subTreeSize[child];
            subTreeSum[root] += subTreeSum[child] + subTreeSize[child];
        }
    }
    
    private void postprocess(int root, int[] res, Set<Integer> visited) {
        visited.add(root);
        
        for (int child : adj.get(root)) {
            if (visited.contains(child)) continue;
            
            res[child] = res[root] + (N - subTreeSize[child]) - subTreeSize[child];
            postprocess(child, res, visited);
        }
    }
}
