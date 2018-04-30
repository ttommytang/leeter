import java.util.*;
import java.util.LinkedList;

public class Graph {
    // This is a simple playground for graph-related problems
    // Internally, use adjacent list for graph representation
    private int V;
    private LinkedList<Integer> graph[];
    
    public Graph(int v) {
        V = v;
        graph = new LinkedList[v];
        
        for (int i = 0; i < v; i++)
            graph[i] = new LinkedList<>();
    }
    
    public boolean addEdge(int from, int to) {
        graph[from].add(to);
        return true;
    }
    
    List<Integer> topoSort() {
        boolean visited[] = new boolean[V];
        Arrays.fill(visited, false);
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < V; i++)
            if (!visited[i])
                topoSortUtil(i, stack, visited);
        
        List<Integer> res = new ArrayList<>();
        
        while(stack.isEmpty()) res.add(stack.pop());
        
        return res;
    }
    
    void topoSortUtil(int at, Stack<Integer> stack, boolean[] visited) {
        visited[at] = true;
        
        for (int next : graph[at])
            if (!visited[next])
                topoSortUtil(next, stack, visited);
        
        stack.push(at);
    }
}
