import java.util.*;

public class WordReplace {
    public static void main(String[] args) {
        List<String> dict = Arrays.asList("cat", "bat", "rat");
        WordReplace test = new WordReplace();

        String t1 = test.replaceWords(dict, "the cattle was rattled by the battery");
    }

    public String replaceWords(List<String> dict, String sentence) {
        if (dict == null || dict.size() == 0) return sentence;

        StringBuilder res = new StringBuilder();
        int wordHead = 0, wordTail = 0;
        TrieNode root = buildTreeFromDict(dict);

        while(wordHead < sentence.length()) {
            int oldTail = wordHead;
            while(wordHead < sentence.length() && sentence.charAt(wordHead) == ' ') wordHead++;

            // Append possible one or more spaces before word
            res.append(sentence.substring(oldTail, wordHead));

            wordTail = wordHead;
            while(wordTail < sentence.length() && sentence.charAt(wordTail) != ' ') wordTail++;

            appendOneWord(root, sentence, wordHead, wordTail, res);
            wordHead = wordTail;
        }

        return res.toString();
    }

    private void appendOneWord(TrieNode root, String sentence, int head, int tail, StringBuilder cur) {
        int rootUtil = head;

        while(rootUtil < tail) {
            if (root.isEnd) break;
            char ch = sentence.charAt(rootUtil);

            if (root.children[ch - 'a'] != null) {
                root = root.children[ch - 'a'];
                rootUtil++;
            } else
                break;
        }

        if (root.isEnd) {
            cur.append(sentence.substring(head, rootUtil));
        } else {
            cur.append(sentence.substring(head, tail));
        }

    }

    private TrieNode buildTreeFromDict(List<String> dict) {
        TrieNode root = new TrieNode(), crawl = root;

        for (String prefix : dict) {
            crawl = root;
            for (char ch : prefix.toCharArray()) {
                if (crawl.isEnd) break;
                if (crawl.children[ch - 'a'] == null) {
                    crawl.children[ch - 'a'] = new TrieNode();
                }
                crawl = crawl.children[ch - 'a'];
            }
            crawl.isEnd = true;
        }

        return root;
    }

    static class TrieNode {
        boolean isEnd;
        TrieNode[] children;

        TrieNode() {
            isEnd = false;
            children = new TrieNode[26];
        }
    }
}
