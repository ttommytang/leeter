import java.util.*;
import java.util.LinkedList;

public class CPUTaskScheduler {
    public static void main(String[] args) {
        CPUTaskScheduler test = new CPUTaskScheduler();
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'D', 'D'};
        
        int len = test.leastInterval(tasks, 2);
    }
    
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) return 0;
        
        int[] freq = new int[26];
        for (char task : tasks)
            freq[task - 'A']++;
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int f : freq)
            if (f != 0) maxHeap.offer(f);
    
        int count = 0;
        while(!maxHeap.isEmpty()) {
            int topFreq = maxHeap.poll();
            count++; topFreq--;
            
            int chunk = n;
            Queue<Integer> queue = new LinkedList<>();
            while(topFreq > 0 && chunk > 0) {
                if (!maxHeap.isEmpty()) {
                    int left = maxHeap.poll() - 1;
                    if (left > 0) queue.offer(left);
                }
                count++; chunk--;
                // if (queue.isEmpty() && maxHeap.isEmpty()) break;
            }
            
            if (topFreq > 0) maxHeap.offer(topFreq);
            while(!queue.isEmpty()) maxHeap.offer(queue.poll());
        }
        
        return count;
    }
    
}
