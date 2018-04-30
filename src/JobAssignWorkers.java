import java.util.*;

public class JobAssignWorkers {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        // Assume all input arrays are valid
        Map<Integer, Integer> jobs = new HashMap<>();
        
        for (int i = 0; i < difficulty.length; i++) {
            jobs.put(difficulty[i], Math.max(jobs.getOrDefault(difficulty[i], Integer.MIN_VALUE), profit[i]));
        }
        
        Arrays.sort(difficulty);
        
        int[] bestProfit = new int[difficulty.length];
        bestProfit[0] = jobs.get(difficulty[0]);
        
        for (int i = 1; i < difficulty.length; i++) {
            bestProfit[i] = Math.max(bestProfit[i-1], jobs.get(difficulty[i]));
        }
        
        int res = 0;
        
        for (int work : worker) {
            int idx = Arrays.binarySearch(difficulty, work);
            
            if (idx > -1)
                res += bestProfit[idx];
            else if (idx == -1) continue;
            else {
                idx = -idx - 2;
                res += bestProfit[idx];
            }
        }
        
        return res;
        
    }
}
