import java.util.*;
import java.util.LinkedList;

public class PassMessage {
    public static void main(String[] args) {
    
    }
    /**
     * @param t: the time of each employee to pass a meeage
     * @param subordinate: the subordinate of each employee
     * @return: the time of the last staff recieve the message
     */
    public int deliverMessage(int[] t, int[][] subordinate) {
        // Write your code here
        if (t == null || t.length < 2) return 0;
        
        int nums = t.length;
        int[] timeGetMessage = new int[nums];
        Arrays.fill(timeGetMessage, Integer.MAX_VALUE);
        timeGetMessage[0] = 0;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        
        while(!queue.isEmpty()) {
            int sender = queue.poll();
            int sendTime = timeGetMessage[sender];
            int sendEffort = t[sender];
            
            int[] receivers = subordinate[sender];
            
            for (int receiver : receivers) {
                if (receiver != -1 && sendTime + sendEffort < timeGetMessage[receiver]) {
                    timeGetMessage[receiver] = sendTime + sendEffort;
                    queue.offer(receiver);
                }
            }
        }
        
        int max = 0;
        for (int time : timeGetMessage)
            max = Math.max(max, time);
        
        return max;
    }
}
