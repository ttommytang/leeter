import java.util.*;

public class RedundantEdge {
    public static void main(String[] args) {
        int[][] edges = {{1,2}, {2,3}, {3,4}, {1,4}, {1,5}};
        int[] extra = findRedundantEdgesDSU(edges);
    }
    
    public static int[] findRedundantEdges(int[][] edges) {
        // Initialize an array of list for recording the vertices connections
        ArrayList<Integer>[] graph = new ArrayList[1001];
        Set<Integer> checked = new HashSet<>();
        for (int i = 0; i <= 1000; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            checked.clear();
            // Before adding a new edge, check if the two vertices are already connected somehow, if yes, this is the redundant edge
            if (!graph[edge[0]].isEmpty() && !graph[edge[1]].isEmpty() && dfsCheck(graph, edge[0], edge[1], checked))
                return edge;
            
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        
        return new int[0];
    }
    
    static boolean dfsCheck(List<Integer>[] graph, int from, int to, Set<Integer> checked) {
        if (!checked.contains(from)) {
            checked.add(from);
            
            if (from == to) return true;
            
            for (int next : graph[from]) {
                if (dfsCheck(graph, next, to, checked)) return true;
            }
        }
        return false;
    }
    
    static class DSU {
        int[] parents;
        int[] rank;
        
        public DSU(int size) {
            this.parents = new int[size];
            for (int i = 0; i < size; i++) {
                parents[i] = i;
            }
            rank = new int[size];
        }
        
        public int find(int x) {
            if (x == parents[x]) return x;
            return parents[x] = find(parents[x]);
        }
        
        public boolean union(int x, int y) {
            int xParent = find(x);
            int yParent = find(y);
            
            // x and y are already union-ed, return false
            if (xParent == yParent) return false;
            
            if (rank[xParent] > rank[yParent])
                parents[yParent] = xParent;
            else if (rank[xParent] < rank[yParent])
                parents[xParent] = yParent;
            else {
                parents[yParent] = xParent;
                rank[xParent]++;
            }
            return true;
        }
    }
    
    public static int[] findRedundantEdgesDSU(int[][] edges) {
        DSU dsu = new DSU(1001);
        
        for (int[] edge : edges) {
            if (!dsu.union(edge[0], edge[1]))
                return edge;
        }
        return new int[0];
    }
    
    public int[] findRedundantDirectedConnection(int[][] edges) {
        if (edges == null || edges.length == 0 || edges[0].length == 0) return null;
        
        int len = edges.length;
        int[] parent = new int[len + 1];
    
        int[] newComer = {-1,-1}, preExist = {-1, -1};
        
        for (int[] edge : edges) {
            if (parent[edge[1]] == 0)
                parent[edge[1]] = edge[0];
            else {
                newComer = edge.clone();
                preExist = new int[]{parent[edge[1]], edge[1]};
                
                // Break the new problematic edge
                edge[1] = 0;
            }
        }
        
        for (int i = 0; i <= len; i++) parent[i] = i;
        
        for (int[] edge : edges) {
            if (edge[1] == 0) continue;
            
            if (findRoot(parent, edge[0]) == edge[1]) {
                if (preExist[0] != -1) return preExist;
                else return edge;
            }
            parent[edge[1]] = edge[0];
        }
        return newComer;
    }
    
    private int findRoot(int[] parent, int x) {
        while(x != parent[x]) x = parent[x];
        return x;
    }
    
    
    
    
}
