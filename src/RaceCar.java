import java.util.LinkedList;
import java.util.*;

public class RaceCar {
    public static void main(String[] args) {
        RaceCar test = new RaceCar();
        
        int res = test.racecar(6);
    }
    
    static class Status {
        int position;
        int speed;
        
        public Status(int pos, int spd) {
            position = pos;
            speed = spd;
        }
        
        public String repe() {
            return "" + position + "|" + speed;
        }
    }
    
    public int racecar(int target) {
        if (target == 0) return 0;
        
        Queue<Status> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        Status init = new Status(0, 1);
        queue.offer(init);
        visited.add(init.repe());
        int count = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            
            for (int i = 0; i < size; i++) {
                Status pre = queue.poll();
                
                // Check if target already reachable
                if (pre.position + pre.speed == target) return count;
                
                // Choose to accelerate and return if will not exceed double target
                if (pre.position + pre.speed > 0 && pre.position + pre.speed < 2 * target) {
                    String repeA = "" + (pre.position + pre.speed) + "|" + (2 * pre.speed);
                    
                    if (!visited.contains(repeA)) {
                        visited.add(repeA);
                        queue.offer(new Status(pre.position + pre.speed, 2 * pre.speed));
                    }
                }
    
                String repeR = "" + pre.position + "|" + (pre.speed > 0 ? -1 : 1);
                if (!visited.contains(repeR)) {
                    visited.add(repeR);
                    queue.offer(new Status(pre.position, pre.speed > 0 ? -1 : 1));
                }
            }
        }
        
        return -1;
    }
}
