import java.util.*;

public class GoatLing {
    public static void main(String[] args) {
        GoatLing test = new GoatLing();
        
        String res = test.toGoatLatin("I speak Goat Latin");
    }
    
    public String toGoatLatin(String S) {
        if (S == null || S.length() == 0) return S;
        
        Set<Character> vowel = new HashSet<>();
        vowel.addAll(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        
        char[] s = S.toCharArray();
        StringBuilder res = new StringBuilder();
        
        int i = 0;
        String post = "a";
        while(i < s.length) {
            while(i < s.length && s[i] == ' ') res.append(s[i++]);
            
            boolean c = false;
            char conso = 'a';
            if (vowel.contains(s[i])) res.append(s[i]);
            else {
                c = true;
                conso = s[i];
            }
            
            i++;
            
            while(i < s.length && s[i] != ' ')
                res.append(s[i++]);
            
            if (c) res.append(conso);
            res.append("ma");
            res.append(post);
            post += "a";
        }
        
        return res.toString();
        
    }
}
