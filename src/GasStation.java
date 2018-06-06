import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

public class GasStation {
    public static void main(String[] args) {
        int[] d = {10,14,20,21}, g = {10,5,2,4};
        
        GasStation test = new GasStation();
        int t = test.getTimes(25, 10, d,g);
    }
    public int getTimes(int target, int original, int[] distance, int[] apply) {
        // Write your code here
        Status car = new Status(0, original, -1);
        Queue<Status> q = new LinkedList<>();
        
        q.offer(car);
        int times = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Status st = q.poll();
                
                if (st.at + st.fuel >= target) return times;
                
                int maxReach = st.at + st.fuel;
                
                int bs = Arrays.binarySearch(distance, st.station == -1? 0: st.station, distance.length, maxReach);
                if (bs < 0) bs = -(bs + 1) - 1;
                
                for (int j = (st.station == -1 ? 0:st.station + 1); j <= bs; j++) {
                    int f = st.fuel - (distance[j] - st.at) + apply[j];
                    q.offer(new Status(distance[j], f, j));
                }
            }
            times++;
        }
        
        return -1;
    }
    
    static class Status {
        int at;
        int fuel;
        int station;
        Status(int a, int f, int s) {
            at = a;
            fuel = f;
            station = s;
        }
    }
}
