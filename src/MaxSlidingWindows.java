import java.util.*;
import java.util.LinkedList;

public class MaxSlidingWindows {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k || nums.length == 0) return null;
        
        int[] res = new int[nums.length - k + 1];
        
        Deque<Integer> dq = new LinkedList<>();
        
        // Initialize the dq with the first k elements;
        for (int i = 0; i < k; i++) {
            while(!dq.isEmpty() && dq.peekLast() < nums[i]) dq.pollLast();
            dq.offerLast(nums[i]);
        }
        
        for (int i = k; i < nums.length; i++) {
            res[i-k] = dq.peekFirst();
            
            if (nums[i-k] == dq.peekFirst()) dq.pollFirst();
            while(!dq.isEmpty() && dq.peekLast() < nums[i]) dq.pollLast();
            dq.offerLast(nums[i]);
        }
        res[nums.length - k] = dq.peekFirst();
        
        return res;
    }
}
