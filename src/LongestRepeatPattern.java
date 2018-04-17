import java.util.*;

public class LongestRepeatPattern {
    public static void main(String[] args) {
        LongestRepeatPattern test = new LongestRepeatPattern();
        String s = "aabcbcbcbc";
        String s1 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaazaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaazaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaazaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        
        
        boolean res1 = test.possible(s, 2, 2);
        boolean res2 = test.possible(s, 2, 4);
        boolean res3 = test.possible(s, 2, 6);
        boolean res4 = test.possible(s, 2, 7);
        
        int res5 = test.longestRepeatingSubsequenceIIII("aaa", 2);
        int res6 = test.longestRepeatingSubsequenceIIII(s, 2);
        int res7 = test.longestRepeatingSubsequenceIIII(s1, 100);
        
    }
    /**
     * @param str: The input string
     * @param k: The repeated times
     * @return: The answer
     */
    public int longestRepeatingSubsequenceII(String str, int k) {
        // Write your code here
        if (str == null || str.length() == 0) return 0;
        
        int subHead = 0, subLength = 1, maxLen = 0;
        
        while(subHead < str.length()) {
            if (subHead + subLength > str.length()) {subHead++; continue;}
            
            String pattern = str.substring(subHead, subHead + subLength);
            
            // Search for # of occurrence
            int count = 0, searchHead = subHead;
            while(str.indexOf(pattern, searchHead) != -1) {
                count++;
                searchHead++;
            }
            
            if (count >= k) {
                maxLen = Math.max(maxLen, subLength);
                subLength++;
            } else {
                subHead++;
                subLength = 1;
            }
        }
        
        return maxLen;
    }
    
    public int longestRepeatingSubsequenceIII(String str, int k) {
        if (str == null || str.length() == 0) return 0;
        int maxPatternLen = str.length() - (k-1);
        
        Map<String, Integer> freqMap = new HashMap<>();
        
        for (int i = 0; i < str.length(); i++) {
            for (int j = 1; j <= Math.min(maxPatternLen, str.length() - i); j++) {
                String pattern = i + j == str.length() ? str.substring(i) : str.substring(i, i + j);
                freqMap.put(pattern, freqMap.getOrDefault(pattern, 0) + 1);
            }
        }
        
        int maxLen = 0;
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() >= k)
                maxLen = Math.max(maxLen, entry.getKey().length());
        }
        
        return maxLen;
    }
    
    public int longestRepeatingSubsequenceIIII(String str, int k) {
        if (str == null || str.length() == 0) return 0;
        
        int right = str.length() - (k - 1), left = 1;
        
        while(left < right - 1) {
            int mid = left + (right - left)/2;
            if (possible(str, k, mid)) left = mid;
            else right = mid - 1;
        }
        
        if (possible(str, k, right)) return right;
        else return left;
        
    }
    
    private boolean possible(String str, int k, int targetLen) {
        Map<String, Integer> freqMap = new HashMap<>();
        
        for (int i = 0; i <= str.length() - targetLen; i++) {
            String pattern = i == str.length() - targetLen ? str.substring(i) : str.substring(i, i + targetLen);
            
            freqMap.put(pattern, freqMap.getOrDefault(pattern, 0) + 1);
            
            if (freqMap.get(pattern) >= k) return true;
        }
        return false;
    }
}
