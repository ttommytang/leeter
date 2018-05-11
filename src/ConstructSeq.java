import java.util.*;
import java.util.LinkedList;

public class ConstructSeq {
    public static void main(String[] args) {
        int[] org = {1,2,3};
        List<List<Integer>> seqs = new ArrayList<>();
        
        seqs.add(Arrays.asList(1,2));
        seqs.add(Arrays.asList(1,3));
        seqs.add(Arrays.asList(2,3));
        
        ConstructSeq test = new ConstructSeq();
        
        boolean res = test.sequenceReconstruction(org, seqs);
    }
    
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        if (org == null || org.length == 0 || seqs == null || seqs.size() == 0) return false;
        
        HashMap<Integer, HashSet<Integer>> adj = new HashMap<>();
        int[] inDegree = new int[org.length + 1];
        int n = org.length;
        for (List<Integer> seq : seqs) {
            for (int from = 0; from < seq.size() - 1; from++) {
                if (seq.get(from) > n || seq.get(from) < 1) continue;
    
                int to = from + 1;
                while(to < seq.size() && (seq.get(to) > n || seq.get(to) < 1)) to++;
                if (to >= seq.size()) break;
    
                inDegree[seq.get(to)]++;
    
                HashSet<Integer> nexts = adj.getOrDefault(seq.get(from), new HashSet<>());
                nexts.add(seq.get(to));
                adj.put(seq.get(from), nexts);
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= org.length; i++)
            if (inDegree[i] == 0) q.offer(i);
        
        int i = 0;
        while(!q.isEmpty()) {
            if (q.size() != 1) return false;
            
            int cur = q.poll();
            if (org[i++] != cur) return false;
            
            if (adj.containsKey(cur)) {
                for (int next : adj.get(cur)) {
                    if (--inDegree[next] == 0)
                        q.offer(next);
                }
            } else
                return false;
        }
        
        return i == org.length;
        
    }
}
