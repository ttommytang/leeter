import java.util.ArrayList;
import java.util.List;

public class SplitFiboSeq {
    public static void main(String[] args) {
        SplitFiboSeq test = new SplitFiboSeq();
        List<Integer> res = test.splitIntoFibonacci("0123");
    
    }
    
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        String max = "2147483647";
        
        if (S == null || S.length() < 3)
            return res;
        
        // Skip the head zeros;
        int head = 0;
        while(head < S.length() && S.charAt(head) == '0') {
            head++;
            res.add(0);
        }
        
        for (int i = head+1; i <= S.length() / 2; i++) {
            String a = S.substring(head, i);
            if (a.length() > max.length() || (a.length() == max.length() && a.compareTo(max) > 0))
                break;
            
            for (int j = i+1; j < S.length(); j++) {
                String b = S.substring(i, j);
                if (b.length() > max.length() || (b.length() == max.length() && b.compareTo(max) > 0))
                    break;
                List<Integer> seq = util(S, j, a, b);
                if (seq.size() >= 3) {
                    res.addAll(seq);
                    return res;
                }
                
                if (S.charAt(i) == '0')
                    break;
            }
        }
        
        return new ArrayList<>();
    }
    
    private List<Integer> util(String S, int off, String a, String b) {
        List<Integer> seq = new ArrayList<>();
        
        if (S.length() - off < Math.max(a.length(), b.length()))
            return seq;
        
        int A = Integer.parseInt(a);
        int B = Integer.parseInt(b);
        seq.add(A);
        seq.add(B);
        int idx;
        while(off < S.length()) {
            int C = A + B;
            String strC = String.valueOf(C);
            idx = S.indexOf(strC, off);
            
            if (idx != off)
                break;
            
            seq.add(C);
            A = B;
            B = C;
            off += strC.length();
            
            if (off == S.length())
                return seq;
        }
        return new ArrayList<>();
    }
}
