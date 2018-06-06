import java.util.LinkedList;

public class BackSpaceStr {
    public static void main(String[] args) {
        String s = "y#fo##f", t = "y#f#o##f";
        
        boolean res = new BackSpaceStr().backspaceCompare(s, t);
    }
    public boolean backspaceCompare(String S, String T) {
        if (S == null || T == null)
            return false;
        
        LinkedList<Character> s = new LinkedList<>();
        LinkedList<Character> t = new LinkedList<>();
        
        for (char ch : S.toCharArray()) {
            if (ch == '#' && s.size() > 0)
                s.pollLast();
            else
                s.offerLast(ch);
        }
        
        for (char ch : T.toCharArray()) {
            if (ch == '#' && t.size() > 0)
                t.pollLast();
            else
                t.offerLast(ch);
        }
        
        if (s.size() != t.size())
            return false;
        
        for (int i = 0; i < s.size(); i++) {
            if (s.get(i) != t.get(i))
                return false;
        }
        
        return true;
    }
}
