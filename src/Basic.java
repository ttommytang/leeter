import java.util.*;

public class Basic {
    public static void main(String[] args) {
        Basic test = new Basic();
        
        String s = "catsanddog";
        
        String[] words = {"cat", "cats", "and", "sand", "dog"};
        List<String> dict = Arrays.asList(words);
        
        // boolean isSub = test.wordBreak(s, dict);
        // List<String> sentences = test.wordBreakReturnSentences(s, dict);
        
        List<String> sentences = test.wordBreakWithMEM(s, dict, 0);
    }
    
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null) return false;
        
        boolean[] dp = new boolean[s.length()];
        
        for(int i = 1; i <= s.length(); i++) {
            if (wordDict.contains(s.substring(0, i))) {
                dp[i-1] = true;
                continue;
            }
            
            for(int j = i - 1; j > 0; j--) {
                if(dp[j - 1] && wordDict.contains(s.substring(j, i))) {
                    dp[i-1] = true;
                    break;
                }
            }
        }
        
        return dp[s.length() - 1];
    }
    
    public List<String> wordBreakReturnSentences(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        if (s == null || wordDict == null)
            return res;
        
        int used = 0;
        StringBuilder current = new StringBuilder("");
        
        wordBreakHelper(s, wordDict, used, current, res);
        
        return res;
    }
    
    private void wordBreakHelper(String s, List<String> wordDict, int used, StringBuilder current, List<String> res) {
        if(used >= s.length()) {
            current.setLength(current.length() - 1);
            res.add(current.toString());
            return;
        }
        
        for (int i = used + 1; i <= s.length(); i++) {
            if (wordDict.contains(s.substring(used, i))) {
                int oldLength = current.length();
                current.append(s.substring(used, i));
                current.append(' ');
                wordBreakHelper(s, wordDict, i, current, res);
                current.setLength(oldLength);
            }
        }
    }
    
    HashMap<Integer, List<String>> wordBreakMem = new HashMap<>();
    
    private List<String> wordBreakWithMEM(String s, List<String> wordDict, int used) {
        // MEM for post fix string partitions
        if (wordBreakMem.containsKey(used))
            return wordBreakMem.get(used);
        
        
        List<String> res = new ArrayList<>();
        if (used == s.length())
            res.add("");
        
        for (int i = used + 1; i <= s.length(); i++) {
            if (wordDict.contains(s.substring(used, i))) {
                List<String> posts = wordBreakWithMEM(s, wordDict, i);
                for (String post : posts) {
                    res.add(s.substring(used, i) + (post.equals("") ? "" : " ") + post);
                }
            }
        }
        wordBreakMem.put(used, res);
        return res;
    }
}
