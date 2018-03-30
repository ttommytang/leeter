import java.util.*;

public class RegexMatch {
    public static void main(String[] args) {
        RegexMatch test = new RegexMatch();
        boolean is = test.isMatch("aa", ".*");
    }

    public boolean isMatch(String s, String p) {
        return dfsMatch(s, p, 0, 0);
    }

    private boolean dfsMatch(String s, String p, int atS, int atP) {
        // Return false if pattern is used up but still got more string coming
        if (atP == p.length() && atS < s.length()) return false;
        if (atP == p.length() && atS == s.length()) return true;

        char pToMatch = p.charAt(atP);

        if (pToMatch == '.') {
            // Matching zero or more of any char
            if (atP + 1 < p.length() && p.charAt(atP + 1) == '*') if (atS < s.length()) {
                // Consume 0 - n of these chars
                for (int toS = atS; toS <= s.length(); toS++)
                    if (dfsMatch(s, p, toS, atP + 2))
                        return true;
                return false;
            } else
                return dfsMatch(s, p, atS, atP + 2);
            else return atS < s.length() && dfsMatch(s, p, atS + 1, atP + 1);
        } else {
            if (atP + 1 < p.length() && p.charAt(atP + 1) == '*') if (atS < s.length()) {
                int endMatch = atS;
                while (endMatch < s.length() && s.charAt(endMatch) == pToMatch) endMatch++;
                for (int toS = atS; toS <= endMatch; toS++)
                    if (dfsMatch(s, p, toS, atP + 2))
                        return true;
                return false;
            } else return dfsMatch(s, p, atS, atP + 2);
            else return atS < s.length() && s.charAt(atS) == pToMatch && dfsMatch(s, p, atS + 1, atP + 1);
        }
    }
}
