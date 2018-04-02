import java.util.*;

public class ExpressiveWord {
    public int expressiveWords(String S, String[] words) {
        if (S == null || S.length() == 0 || words == null || words.length == 0) return 0;
        
        TrieNode root = new TrieNode(0), crawl = root;
        char[] s = S.toCharArray();
        int i = 0;
        while(i < s.length) {
            char ch = s[i];
            int count = 0;
            while(i + count < s.length && s[i + count] == ch) count++;
    
            crawl.children[ch - 'a'] = new TrieNode(count);
            crawl = crawl.children[ch - 'a'];
            i += count;
        }
        crawl.isEnd = true;
        
        int res = 0;
        for (String word : words) {
            crawl = root; i = 0;
            boolean stretchy = true;
            char[] wordCH = word.toCharArray();
            while(i < word.length()) {
                char ch = wordCH[i];
                int count = 0;
                while(i + count < word.length() && wordCH[i + count] == ch) count++;
                
                if (crawl.children[ch - 'a'] == null) { stretchy = false; break; }
                
                crawl = crawl.children[ch - 'a'];
                int sCount = crawl.count;
                
                if (sCount < 3 && sCount != count) { stretchy = false; break; }
                if (count > sCount) { stretchy = false; break; }
                i += count;
            }
            
            if (stretchy && crawl.isEnd) res++;
        }
        
        return res;
    }
    
    static class TrieNode {
        TrieNode[] children;
        int count;
        boolean isEnd;
        
        TrieNode(int count) {
            children = new TrieNode[26];
            this.count = count;
        }
    }
}
