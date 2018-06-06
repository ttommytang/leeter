import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class GraphCenter {
    public static void main(String[] args) {
        int[] x = {1,2,2};
        int[] y = {2,3,4};
        int[] d = {1,1,1};
        
        GraphCenter test = new GraphCenter();
//        test.buildGraph(x,y,d);
//        test.countInitSum(1, 0, new HashSet<>());
//        test.countSubtreeSize(1, new HashSet<>());
//
//        System.err.println("Init sum: " + test.sumDist);
//        for (Map.Entry<Integer, Integer> entry : test.subtreeSize.entrySet()) {
//            System.err.println("SubTree: root " + entry.getKey() + " size " + entry.getValue());
//        }
//        test.findFermatHelper(1, 5, 8, new HashSet<>());
        
        System.err.println("Center: " + test.getFermatPoint(x, y, d));
        
    }
    /*
    There is a non acyclic connected graph. Each edge is described by two vertices x[i] and y[i], and the length of each
    edge is described by d[i]. Find a point p such that the sum of distances from point p to other points is the smallest.
    If there is more than one such point p, return the smallest number.
     */
    private int center;
    private long sumDist;
    private HashMap<Integer, HashMap<Integer, Integer>> graph;
    private HashMap<Integer, Integer> subtreeSize;
    
    /**
     * @param x: The end points set of edges
     * @param y: The end points set of edges
     * @param d: The length of edges
     * @return: Return the index of the fermat point
     */
    public int getFermatPoint(int[] x, int[] y, int[] d) {
        // Write your code here
        if (x == null || y == null || d == null || x.length == 0 || y.length == 0 || d.length == 0 || x.length != y.length || x.length != d.length)
            return -1;
        
        buildGraph(x, y, d);
        int virtualRoot = x[0];
        center = virtualRoot;
        countInitSum(virtualRoot, 0, new HashSet<>());
        int totalSize = countSubtreeSize(virtualRoot, new HashSet<>());
        
        findFermatHelper(virtualRoot, totalSize, sumDist, new HashSet<>());
        return center;
    }
    
    private void buildGraph(int[] x, int[] y, int[] d) {
        // Build the adjacent list graph based on the edges
        graph = new HashMap<>();
        subtreeSize = new HashMap<>();
        
        for (int i = 0; i < x.length; i++) {
            graph.putIfAbsent(x[i], new HashMap<>());
            graph.get(x[i]).put(y[i], d[i]);
    
            graph.putIfAbsent(y[i], new HashMap<>());
            graph.get(y[i]).put(x[i], d[i]);
        }
    }
    
    private void countInitSum(int root, long path, HashSet<Integer> visited) {
        sumDist += path;
        visited.add(root);
        
        for (Map.Entry<Integer, Integer> entry : graph.get(root).entrySet()) {
            if (!visited.contains(entry.getKey())) {
                countInitSum(entry.getKey(), path + entry.getValue(), visited);
            }
        }
    }
    
    private int countSubtreeSize(int root, HashSet<Integer> visited) {
        // Count the subtree sizes based on a picked root
        int count = 1;
        visited.add(root);
        for (int next : graph.get(root).keySet()) {
            if (!visited.contains(next)) {
                count += countSubtreeSize(next, visited);
            }
        }
        
        subtreeSize.put(root, count);
        return count;
    }
    
    private void findFermatHelper(int root, int nodeCount, long currentSum, HashSet<Integer> visited) {
        visited.add(root);
        // swap the 'root' role between the current root and all its children, update the min
        for (Map.Entry<Integer, Integer> entry : graph.get(root).entrySet()) {
            if (visited.contains(entry.getKey()))
                continue;
            
            int bridge = entry.getValue();
            int child = entry.getKey();
            long newSum = currentSum - bridge * subtreeSize.get(child) + bridge * (nodeCount - subtreeSize.get(child));
            if (newSum <= sumDist) {
                center = newSum < sumDist ? child : Math.min(child, center);
                sumDist = newSum;
            }
            
            findFermatHelper(child, nodeCount, newSum, visited);
        }
    }
}
