import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindNonRepeatSubstring {
    /**
     * @param str: The string
     * @param k: The length of the substring
     * @return: The answer
     */
    public int findSubstring(String str, int k) {
        // Write your code here
        if (str == null || str.length() < k) return 0;
        
        Map<Character, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        StringBuilder substr = new StringBuilder();
        
        for (int i = 0; i < k; i++) {
            char ch = str.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            
            substr.append(ch);
        }
        if (map.size() == k) set.add(substr.toString());
        for (int i = k; i < str.length(); i++) {
            char toRemove = str.charAt(i-k);
            char toAdd = str.charAt(i);
            
            int left = map.get(toRemove) - 1;
            if (left == 0) map.remove(toRemove);
            else map.put(toRemove, left);
            
            int added = map.getOrDefault(toAdd, 0) + 1;
            map.put(toAdd, added);
            
            substr.deleteCharAt(0);
            substr.append(toAdd);
            
            if (map.size() == k) set.add(substr.toString());
        }
        
        return set.size();
        
    }
}
