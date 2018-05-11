import java.util.*;
import java.util.LinkedList;

public class MInHeightTree {
    public static void main(String[] args) {
        MInHeightTree test = new MInHeightTree();
        
        int[][] edges = {{0,1}, {0,2}, {0,3}};
        List<Integer> roots = test.findMinHeightTrees(4, edges);
    }
    
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        LinkedList<Integer> res = new LinkedList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        
        List<HashSet<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new HashSet<>());
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        for (int i = 0; i < n; i++)
            if (graph.get(i).size() == 1) res.add(i);
        
        while(n > 2) {
            n -= res.size();
            LinkedList<Integer> newLeaved = new LinkedList<>();
            for (int leaf : res) {
                int nei = graph.get(leaf).iterator().next();
                graph.get(nei).remove(leaf);
                if (graph.get(nei).size() == 1)
                    newLeaved.add(nei);
            }
            res = newLeaved;
        }
        
        return res;
    }
}
