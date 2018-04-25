import java.util.*;
import java.util.LinkedList;

public class ShortestToChar {
    public static void main(String[] args) {
        ShortestToChar test = new ShortestToChar();
        
        int[] res = test.shortestToChar("loveleetco", 'e');
        
    }

    public int[] shortestToChar(String S, char C) {
        if (S == null || S.length() == 0) {
        	return new int[0];
        }

        int len = S.length(), i = 0, PREV = -1 - len;
        List<Integer> stops = new LinkedList<>();
        for (int j = 0; j < len; j++) {
        	if (S.charAt(j) == C) {
        		stops.add(j);
        	}
        }

        int[] res = new int[len];
        Iterator<Integer> it = stops.iterator();
        int CUR = it.next();
        while(i < len) {
        	while(i < len && Math.abs(i - PREV) < Math.abs(i - CUR)) {
        	    res[i] = Math.abs(i - PREV);
        	    i++;
            }
            PREV = CUR;
        	CUR = it.hasNext() ? it.next() : Integer.MAX_VALUE;
        }
        
        return res;
    }
}
