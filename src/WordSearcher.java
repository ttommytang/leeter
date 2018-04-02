import java.util.*;

public class WordSearcher {
    public static void main(String[] args) {
        WordSearcher test = new WordSearcher();

//        char[][] board = {{'o','a','a','n'}, {'e','t','a','e'}, {'i','h','k','r'}, {'i','f','l','v'}};
//        String[] words = {"oath","pea","eat","rain"};

//        char[][] board = {{'a','a'}};
//        String[] words = {"aaa"};

        char[][] board = {{'a','b'}, {'c', 'd'}};
        String[] words = {"ab","cb","ad","bd","ac","ca","da","bc","db","adcb","dabc","abb","acb"};

        // Test cases go here
        List<String> res = test.findWords(board, words);
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) return res;
        TrieNode root = prebuildTrie(words);
        StringBuilder current = new StringBuilder();

        Set<String> resSet = new HashSet<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                dfsFindWord(board, visited, root, current, resSet, i, j);
            }
        }

        res.addAll(resSet);
        return res;
    }

    private void dfsFindWord(char[][] board, boolean[][] visited, TrieNode root, StringBuilder currentStr, Set<String> res, int i, int j) {
        char ch = board[i][j];
        // Dead end -> just return
        if (root.children[ch - 'a'] == null) return;


        currentStr.append(ch);
        visited[i][j] = true;
        root = root.children[ch - 'a'];
        if (root.isWordEnd)
            res.add(currentStr.toString());

        if (i > 0 && !visited[i-1][j])
            dfsFindWord(board, visited, root, currentStr, res, i - 1, j);
        if (i < board.length - 1 && !visited[i+1][j])
            dfsFindWord(board, visited, root, currentStr, res, i + 1, j);
        if (j > 0 && !visited[i][j-1])
            dfsFindWord(board, visited, root, currentStr, res, i, j - 1);
        if (j < board[0].length - 1 && !visited[i][j+1])
            dfsFindWord(board, visited, root, currentStr, res, i, j + 1);

        currentStr.deleteCharAt(currentStr.length() - 1);
        visited[i][j] = false;
    }

    private TrieNode prebuildTrie(String[] words) {
        TrieNode root = new TrieNode(' ', false);

        TrieNode crawl = root;

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                boolean isEnd = false;
                if (i == word.length() - 1)
                    isEnd = true;

                TrieNode child;
                if (crawl.children[ch - 'a'] == null) {
                    child = new TrieNode(ch, isEnd);
                    crawl.children[ch - 'a'] = child;
                } else {
                    child = crawl.children[ch - 'a'];
                    if (!child.isWordEnd)
                        child.isWordEnd = isEnd;
                }
                crawl = child;
            }

            crawl = root;
        }

        return root;
    }

    static class TrieNode {
        char ch;
        boolean isWordEnd;
        TrieNode[] children;

        public TrieNode(char ch, boolean isEnd) {
            this.ch = ch;
            this.isWordEnd = isEnd;
            this.children = new TrieNode[26];
        }
    }
}
