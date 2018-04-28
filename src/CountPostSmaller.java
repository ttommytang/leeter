import java.util.*;
import java.util.LinkedList;

public class CountPostSmaller {
    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(1,2,3,5,5,5,6);
        
        int t1 = Collections.binarySearch(arr, 2);
        int t2 = Collections.binarySearch(arr, 4);
        int t3 = Collections.binarySearch(arr, 5);
        
        
    }
    
    /** O(N log N) method -> TLE
     * @param nums: a list of integers
     * @return: return a list of integers
     */
    public List<Integer> countSmaller(int[] nums) {
        // write your code here
        LinkedList<Integer> res = new LinkedList<>();
        if (nums == null || nums.length == 0) return res;
    
        List<Integer> sorted = new LinkedList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int idx = Collections.binarySearch(sorted, nums[i]);
            if (idx < 0) {
                idx = -idx - 1;
                res.addFirst(idx);
                sorted.add(idx, nums[i]);
            } else {
                res.addFirst(idx);
                sorted.add(idx, nums[i]);
            }
        }
        return res;
    }
    
    public String[] logSort(String[] logs) {
        // Write your code here
        if (logs == null || logs.length <= 1) return logs;
        
        Arrays.sort(logs, (String log1, String log2) -> {
            int split1 = log1.indexOf(' ');
            int split2 = log2.indexOf(' ');
            
            int contentCompare = log1.substring(split1 + 1).compareTo(log2.substring(split2 + 1));
            if (contentCompare == 0)
                return log1.substring(0, split1).compareTo(log2.substring(0, split2));
            else
                return contentCompare;
        });
        
        return logs;
    }
    
    
}
