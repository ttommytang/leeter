import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class AlienDictionary {
    public static void main(String[] args) {
        String[] words = {"za","zb","ca","cb"};
        
        AlienDictionary test = new AlienDictionary();
        String res = test.alienOrder(words);
    }
    
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0)
            return "";
        if (words.length == 1)
            return words[0];
        
        HashMap<Character, HashSet<Character>> graph = new HashMap<>();
        HashSet<Character> vertices = new HashSet<>();
        HashSet<Character> extras = new HashSet<>();
        int[] indegree = new int[26];
        
        for (int i = 1; i < words.length; i++) {
            util(words[i-1], words[i], graph, indegree, vertices, extras);
        }
        
        return topoSort(graph, indegree, vertices, extras);
    }
    
    private void util(String former, String latter, HashMap<Character, HashSet<Character>> graph,
                      int[] indegree, HashSet<Character> vertices, HashSet<Character> extras) {
        int i = 0, len = Math.min(former.length(), latter.length());
        while(i < len && former.charAt(i) == latter.charAt(i)) {
            extras.add(former.charAt(i));
            i++;
        }
        int j = i, k = i;
        while(k < former.length())
            extras.add(former.charAt(k++));
        while(j < latter.length())
            extras.add(latter.charAt(j++));
        
        
        if (i == len)
            return;
        
        char from = former.charAt(i), to = latter.charAt(i);
        vertices.add(from);
        vertices.add(to);
        graph.putIfAbsent(from, new HashSet<>());
        if (!graph.get(from).contains(to)) {
            graph.get(from).add(to);
            indegree[to - 'a']++;
        }
    }
    
    private String topoSort(HashMap<Character, HashSet<Character>> graph, int[] indegree,
                            HashSet<Character> vertices, HashSet<Character> extras) {
        StringBuilder res = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        
        for (char v = 'a'; v <= 'z'; v++) {
            if (vertices.contains(v) && indegree[v-'a'] == 0)
                q.offer(v);
        }
        
        while(!q.isEmpty()) {
            char cur = q.poll();
            if (graph.containsKey(cur)) {
                for (char next : graph.get(cur)) {
                    indegree[next - 'a']--;
                    if (indegree[next - 'a'] == 0)
                        q.offer(next);
                }
            }
            res.append(cur);
        }
        
        if (res.length() == vertices.size()) {
            for (char e : extras)
                if (!vertices.contains(e))
                    res.append(e);
            return res.toString();
        } else
            return "";
        
    }
}
