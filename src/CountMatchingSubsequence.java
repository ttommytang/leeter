import java.util.*;

public class CountMatchingSubsequence {
    static class SubSeqNode {
        String word;
        int atIndex;
        SubSeqNode(String str) {
            word = str;
            atIndex = 0;
        }
    }

    public int numMatchingSubseq(String S, String[] words) {
        if (S == null || S.length() == 0) return 0;

        // Initialize the prefix words map
        List<SubSeqNode>[] subSeqMap = new ArrayList[26];
        for (int i = 0; i < 26; i++)
            subSeqMap[i] = new ArrayList<SubSeqNode>();

        // Add all words into the map according to their head char
        for (String word : words) {
            SubSeqNode node = new SubSeqNode(word);
            subSeqMap[word.charAt(0) - 'a'].add(node);
        }

        // Traverse the String input, and shuffle the map along the way, increment count if reach the end of the word
        int count = 0;
        for (char ch : S.toCharArray()) {
            // Fetch the old node list and replace with an empty one
            List<SubSeqNode> oldList = subSeqMap[ch - 'a'];
            subSeqMap[ch - 'a'] = new ArrayList<>();

            for (SubSeqNode node : oldList) {
                node.atIndex++;
                if (node.atIndex >= node.word.length())
                    count++;
                else
                    subSeqMap[node.word.charAt(node.atIndex) - 'a'].add(node);
            }
        }
        return count;
    }
    
    public boolean isSubSequence(String shortStr, String longStr) {
        if (shortStr.length() > longStr.length()) return false;
        
        int i = 0;
        for (char ch : longStr.toCharArray()) {
            if (i < shortStr.length() && ch == shortStr.charAt(i))
                i++;
        }
        
        return i == shortStr.length();
    }
}
