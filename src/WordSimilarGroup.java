import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

public class WordSimilarGroup {
    public static void main(String[] args) {
        WordSimilarGroup test = new WordSimilarGroup();
        String[] dict = {"tars","rats","arts","star"};
        
        int n = test.numSimilarGroups(dict);
    }
    
    public int numSimilarGroups(String[] A) {
        if (A == null || A.length == 0) return 0;
    
        int N = A.length, L = A[0].length();
        if (N > L * L) {
            return shortStrGroups(A);
        } else {
            return longStrGroup(A);
        }
    }
    
    private int shortStrGroups(String[] A) {
        HashSet<String> dict = new HashSet<>(Arrays.asList(A));
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        HashSet<String> visited = new HashSet<>();
    
        for (String word : A) {
            if (visited.contains(word))
                continue;
            visited.add(word);
            map.put(word, new ArrayList<>());
            expand(word, new StringBuilder(word), map, dict, visited);
        }
    
        return map.size();
    }
    
    private int longStrGroup(String[] A) {
        DSU dsu = new DSU(A.length);
        
        for (int i = 0; i < A.length; i++) {
            for (int j = i+1; j < A.length; j++) {
                if (isSimilar(A[i], A[j]))
                    dsu.union(i, j);
            }
        }
        
        int count = 0;
        for (int i = 0; i < dsu.parent.length; i++) {
            if (i == dsu.find(i))
                count++;
        }
        
        return count;
    }
    
    private void expand(String root, StringBuilder cur, HashMap<String, ArrayList<String>> map, HashSet<String> dict, HashSet<String> visited) {
        for (int i = 0; i < cur.length(); i++) {
            for (int j = i+1; j < cur.length(); j++) {
                String nStr = swap(cur, i, j);
                if (dict.contains(nStr) && !visited.contains(nStr)) {
                    map.get(root).add(nStr);
                    visited.add(nStr);
                    expand(root, new StringBuilder(nStr), map, dict, visited);
                }
            }
        }
    }
    
    private String swap(StringBuilder str, int l, int r) {
        char temp = str.charAt(l);
        str.setCharAt(l, str.charAt(r));
        str.setCharAt(r, temp);
        
        String res = str.toString();
        
        temp = str.charAt(l);
        str.setCharAt(l, str.charAt(r));
        str.setCharAt(r, temp);
        
        return res;
    }
    
    private boolean isSimilar(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i))
                diff++;
        }
        return diff == 2;
    }
    
    static class DSU {
        int[] parent;
        int[] rank;
        
        DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = i;
            rank = new int[n];
        }
        
        int find(int x) {
            if (x == parent[x]) return x;
            return parent[x] = find(parent[x]);
        }
        
        void union(int x, int y) {
            int xParent = find(x);
            int yParent = find(y);
            
            if (xParent == yParent) return;
            
            if (rank[xParent] < rank[yParent]) {
                int temp = xParent;
                xParent = yParent;
                yParent = temp;
            }
            
            parent[yParent] = xParent;
            if (rank[xParent] == rank[yParent])
                rank[xParent]++;
        }
        
    }
}
