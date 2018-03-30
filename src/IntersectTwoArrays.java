import java.util.*;

public class IntersectTwoArrays {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length <= 0 || nums2.length <= 0)
            return new int[0];
        
        // Put numbers in nums1 array into a hashmap, recording the frequency as well
        java.util.Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>();
        for (int num : nums1) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // Then for numbers in nums2 array, searching in the hashmap then add into the result array if find
        // also need to reduce the frequency to make sure that no more than what's presented in nums1 array
        List<Integer> result = new ArrayList<>();
        for (int num : nums2) {
            if (freqMap.containsKey(num)) {
                result.add(num);
                
                if (freqMap.get(num) - 1 <= 0)
                    freqMap.remove(num);
                else
                    freqMap.put(num, freqMap.get(num) - 1);
            }
        }
        
        int[] res = new int[result.size()];
        for(int i = 0; i < res.length; i++) {
            res[i] = result.get(i);
        }
        
        return res;
    }
}
