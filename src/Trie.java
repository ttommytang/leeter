public class Trie {
    // Nested class for Trie node
    static class TrieNode {
        String word;
        TrieNode[] children;
        public TrieNode(String w) {
            this.word = w;
            this.children = new TrieNode[26];
        }
    }

    TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        this.root = new TrieNode("");
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode crawl = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (crawl.children[ch - 'a'] == null) {
                crawl.children[ch - 'a'] = new TrieNode("");
            }
            crawl = crawl.children[ch - 'a'];
        }
        crawl.word = word;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode crawl  = root;

        for (char ch : word.toCharArray()) {
            if (crawl == null) return false;
            crawl = crawl.children[ch - 'a'];
        }

        return crawl != null && crawl.word.equals(word);
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode crawl  = root;

        for (char ch : prefix.toCharArray()) {
            if (crawl == null) return false;
            crawl = crawl.children[ch - 'a'];
        }

        return crawl != null;
    }
}
