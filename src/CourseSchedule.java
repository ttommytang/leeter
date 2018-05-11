import java.util.*;
import java.util.LinkedList;

public class CourseSchedule {
    public static void main(String[] args) {
        int[][] mat = {{0,1}, {0,2}};
        CourseSchedule test = new CourseSchedule();
        
        boolean res = test.canFinish(3, mat);
    }
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses < 2 || prerequisites == null || prerequisites.length == 0) return true;
        
        int[] inDegree = new int[numCourses];
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] edge : prerequisites) {
            Set<Integer> adj = graph.getOrDefault(edge[0], new HashSet<>());
            adj.add(edge[1]);
            graph.put(edge[0], adj);
            
            inDegree[edge[1]]++;
        }
    
        // --------- test print all topo sequences -------------
        boolean[] visited = new boolean[numCourses];
        List<List<Integer>> all = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
    
        allTopoSortUtil(graph, inDegree.clone(), visited, cur, all);
        // -----------------------------------------------------
        Queue<Integer> toSort = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) toSort.offer(i);
        }
        
        if (toSort.isEmpty()) return false;
        List<Integer> topo = topoSortUtil(graph, toSort, inDegree);
        return topo.size() == numCourses;
    }
    
    private List<Integer> topoSortUtil(Map<Integer, Set<Integer>> graph, Queue<Integer> queue, int[] inDegree) {
        List<Integer> res = new ArrayList<>();
        
        while(!queue.isEmpty()) {
            int course = queue.poll();
            res.add(course);
            
            if (graph.containsKey(course)) {
                for (int next : graph.get(course))
                    if (--inDegree[next] == 0)
                        queue.offer(next);
            }
        }
        
        return res;
    }
    
    private void allTopoSortUtil(Map<Integer, Set<Integer>> graph, int[] inDegree, boolean[] visited, List<Integer> current, List<List<Integer>> all) {
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0 && !visited[i]) {
                current.add(i);
                visited[i] = true;
                
                if (current.size() == inDegree.length) {
                    all.add(new ArrayList<>(current));
                    current.remove(current.size() - 1);
                    visited[i] = false;
                    return;
                }
                
                if (graph.containsKey(i)) {
                    for (int next : graph.get(i))
                        inDegree[next]--;
                }
                
                allTopoSortUtil(graph, inDegree, visited, current, all);
                
                // Back tracking
                current.remove(current.size() - 1);
                visited[i] = false;
                
                if (graph.containsKey(i)) {
                    for (int next : graph.get(i))
                        inDegree[next]++;
                }
            }
        }
    }
}
