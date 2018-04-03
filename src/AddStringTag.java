import java.util.*;

public class AddStringTag {
    public static void main(String[] args) {
        String s1 = "aaabbcc", s2 = "aaaaaaaaaaa", s3 = "bcbcbcbb", s4 = "aab";
        String[] dict = {"aaa","aab","bc"};
        
        AddStringTag test = new AddStringTag();
        String tagged1 = test.addBoldTag(s1, dict);
        String tagged2 = test.addBoldTag(s2, dict);
        String tagged3 = test.addBoldTag(s3, dict);
        String tagged4 = test.addBoldTag(s4, dict);
        
    }
    public String addBoldTag(String s, String[] dict) {
        if (s == null || s.length() == 0) return s;
        
        int len = s.length();
        int[] substringMatchedRange = new int[len];
        
        for (String word: dict) {
            for (int start = 0; start <= len - word.length(); start++) {
                int matchedHead = s.indexOf(word, start);
                if (matchedHead != -1) {
                    substringMatchedRange[matchedHead]++;
                    if (matchedHead + word.length() < len)
                        substringMatchedRange[matchedHead + word.length()]--;
                }
            }
        }
        
        for (int i = 1; i < len; i++) {
            substringMatchedRange[i] += substringMatchedRange[i-1];
        }
        
        StringBuilder res = new StringBuilder();
        
        int tagHead = 0, tagTail = 0;
        while(tagHead < len) {
            while(tagHead < len && substringMatchedRange[tagHead] <= 0) tagHead++;
            if (tagHead > tagTail) res.append(s, tagTail, tagHead);
            tagTail = tagHead;
            
            while(tagTail < len && substringMatchedRange[tagTail] > 0) tagTail++;
            if (tagTail > tagHead) {
                res.append("<b>");
                res.append(s, tagHead, tagTail);
                res.append("</b>");
                tagHead = tagTail;
            }
        }
        return res.toString();
    }
}
