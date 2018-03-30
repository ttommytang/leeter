import java.util.*;

public class AllPaths {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        
        if(graph == null || graph.length == 0) return res;
        int numOfNodes = graph.length;
        
        List<Integer> path = new ArrayList<>();
        
        path.add(0);
        
        findPath(graph, 0, path, res);
        
        return res;
    }
    
    private void findPath(int[][] graph, int standAt, List<Integer> path, List<List<Integer>> res) {
        if (standAt == graph.length - 1) {
            res.add(new ArrayList<>(path));
        }
        
        for(int next : graph[standAt]) {
            path.add(next);
            findPath(graph, next, path, res);
            path.remove(path.size() - 1);
        }
        
    }
}
