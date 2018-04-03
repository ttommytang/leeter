import java.util.*;

public class LongestDistinctSubstring {
    public static void main(String[] args) {
        LongestDistinctSubstring test = new LongestDistinctSubstring();
        int t1 = test.lengthOfLongestSubstringKDistinct("eceekab", 2);
    }
    
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> freqMap = new HashMap<>();
        int slow = 0, fast = 0, len = 0;
        while(fast < s.length()) {
            char ch = s.charAt(fast);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
            
            if (freqMap.size() <= k) len = Math.max(len, fast - slow + 1);
            else {
                // If there are more than K distinct chars, need to slide window until less
                while (freqMap.size() > k) {
                    char purge = s.charAt(slow++);
                    freqMap.put(purge, freqMap.get(purge) - 1);
                    if (freqMap.get(purge) == 0) freqMap.remove(purge);
                }
            }
            fast++;
        }
        return len;
    }
}
