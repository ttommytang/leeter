import java.util.*;
import java.util.LinkedList;

public class BusRoutes {
    public static void main(String[] args) {
        int[][] routes = {{1,2,7}, {3,6,7}};
        BusRoutes test = new BusRoutes();
        
        int bus = test.numBusesToDestination(routes, 1, 6);
    }
    
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (routes == null || routes.length == 0) return -1;
        
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                if (map.containsKey(stop))
                    map.get(stop).add(i);
                else {
                    Set<Integer> set = new HashSet<>();
                    set.add(i);
                    map.put(stop, set);
                }
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> bussed = new HashSet<>();
        queue.offer(S);
        int taken = 0;
        
        while(!queue.isEmpty()) {
            int via = queue.size();
            while(via > 0) {
                int at = queue.poll();
                Set<Integer> canTake = map.get(at);
                for (int bus : canTake) {
                    if (bussed.contains(bus)) continue;
                    int[] canReach = routes[bus];
                    for (int to : canReach) {
                        if (to == T) return taken + 1;
                        if (to != at) queue.offer(to);
                    }
                    bussed.add(bus);
                }
                via--;
            }
            taken++;
        }
        return -1;
    }
}
