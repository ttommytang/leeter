import java.util.*;

public class MapSum {
    public static void main(String[] args) {
        MapSum test = new MapSum();

        test.insert("apple", 3);
        int t1 = test.sum("ap");
        test.insert("app", 2);
        int t2 = test.sum("ap");
        test.insert("apple", 1);
        int t3 = test.sum("ap");
    }

    TrieNode root;
    Map<String, Integer> map;

    public MapSum() {
        root = new TrieNode();
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        TrieNode crawl = root;
        if (key == null || key.length() == 0) return;

        // If this key already exists, in order to override, need to substitute the original value and add th new value along the path
        if (map.containsKey(key)) {
            int valDiff = -map.get(key) + val;
            map.put(key, val);
            val = valDiff;
        } else
            map.put(key, val);

        for (char ch : key.toCharArray()) {
            if (crawl.children[ch - 'a'] == null) crawl.children[ch - 'a'] = new TrieNode();

            crawl.value += val;
            crawl = crawl.children[ch - 'a'];
        }

        crawl.value += val;
    }

    public int sum(String prefix) {
        TrieNode crawl = root;
        for (char ch : prefix.toCharArray()) {
            if (crawl.children[ch - 'a'] == null) return 0;
            crawl = crawl.children[ch - 'a'];
        }
        return crawl.value;
    }

    static class TrieNode {
        int value;
        TrieNode[] children;

        public TrieNode() {
            this.value = 0;
            this.children = new TrieNode[26];
        }
    }
}
