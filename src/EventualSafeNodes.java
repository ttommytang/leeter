import java.util.*;

public class EventualSafeNodes {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        
        for(int i = 0; i < graph.length; i++) {
            if(helper(graph, i))
                res.add(i);
        }
        
        return res;
        
    }
    
    private boolean helper(int[][] graph, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        
        while(queue.size() > 0) {
            int cur = queue.poll();
            if (cur == start) return false;
            
            for(int n : graph[cur]) {
                if(n == start) return false;
                queue.offer(n);
            }
        }
        
        return true;
        
    }
}
