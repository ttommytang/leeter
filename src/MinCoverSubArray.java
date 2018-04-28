import java.util.*;

public class MinCoverSubArray {
    public static void main(String[] args) {
        String[] tags = {"made","in","china"};
        String[] list = {"made", "a","b","c","in", "china","made","b","c","d"};
        
        MinCoverSubArray test = new MinCoverSubArray();
        
        int res = test.getMinimumStringArray(tags, list);
    }
    
    public int getMinimumStringArray(String[] tagList, String[] allTags) {
        // Write your code here
        if (tagList == null || allTags == null || tagList.length == 0 || allTags.length == 0) return 0;
        
        Map<String, Integer> toMatch = new HashMap<>();
        
        for (String tag : tagList)
            toMatch.put(tag, toMatch.getOrDefault(tag, 0) + 1);
        
        int tagsCount = tagList.length;
        int min = Integer.MAX_VALUE, h = 0;
        
        for (int t = 0; t < allTags.length; t++) {
            if (!toMatch.containsKey(allTags[t])) continue;
            
            int left = toMatch.get(allTags[t]) - 1;
            toMatch.put(allTags[t], left);
            
            if (left >= 0) tagsCount--;
            
            if (tagsCount == 0) {
                // Still matched here, shrink
                while(tagsCount == 0) {
                    min = Math.min(min, t - h + 1);
                    
                    if (!toMatch.containsKey(allTags[h])) h++;
                    else {
                        int next = toMatch.get(allTags[h]) + 1;
                        toMatch.put(allTags[h], next);
                        if (next <= 0) {
                            h++; continue;
                        } else {
                            h++; tagsCount++;
                            break;
                        }
                    }
                }
            }
        }
        
        return min;
    }
}
