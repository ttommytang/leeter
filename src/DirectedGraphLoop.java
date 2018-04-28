import java.util.*;

public class DirectedGraphLoop {
    public static void main(String[] args) {
        int[] s = {1,2,3};
        int[] e = {2,3,1};
        
        DirectedGraphLoop test = new DirectedGraphLoop();
        boolean res = test.isCyclicGraph(s, e);
    }
    
    /**
     * @param start: The start points set
     * @param end: The end points set
     * @return: Return if the graph is cyclic
     */
    public boolean isCyclicGraph(int[] start, int[] end) {
        // Write your code here
        // First build adjacent list from the into two arrays
        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < start.length; i++) {
            if (graph.containsKey(start[i]))
                graph.get(start[i]).add(end[i]);
            else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(end[i]);
                graph.put(start[i], list);
            }
        }
        
        for (int from : graph.keySet())
            if (!visited.contains(from) && dfsCheckCircle(graph, new HashSet<Integer>(), visited, from))
                return true;
        return false;
    }
    
    private boolean dfsCheckCircle(Map<Integer, ArrayList<Integer>> graph, Set<Integer> stack, Set<Integer> visited, int current) {
        visited.add(current);
        if (stack.contains(current)) return true;
        
        stack.add(current);
        if (graph.containsKey(current)) {
            for (int next : graph.get(current))
                if (dfsCheckCircle(graph, stack, visited, next)) return true;
        }
        stack.remove(current);
        return false;
    }
}
