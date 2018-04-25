import java.util.*;

class ShortStringEncoding {
	public static void main(String[] args) {

	}

	public int minimumLengthEncoding(String[] words) {
		if (words == null || words.length == 0) return 0;

		TrieNode root = new TrieNode();

		for (String word : words) {
			TrieNode crawl = root;
			for (int i = word.length() - 1; i >= 0; i--) {
				char ch = word.charAt(i);
				if (!crawl.children.containsKey(ch)) {
					crawl.children.put(ch, new TrieNode());
				}
				crawl = crawl.children.get(ch);
			}
		}

		int res = 0, length = 0;
		Queue<TrieNode> queue = new LinkedList<>();
		queue.offer(root);

		while(!queue.isEmpty()) {
			int size = queue.size();
			length++;
			for (int i = 0; i < size; i++) {
				TrieNode cur = queue.poll();

				if (cur.children.size() == 0) 
					res += length + 1;
				else
					queue.addAll(cur.values());
			}
		}

		return res;
    }

    static class TrieNode {
    	HashMap<Character, TrieNode> children;
    	TrieNode() {
    		children = new HashMap<>();
    	}
    }
}