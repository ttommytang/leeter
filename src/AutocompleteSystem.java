import java.util.*;

public class AutocompleteSystem {
    public static void main(String[] args) {
        String[] sents = {"i love you", "island", "ironman", "i love leetcode"};
        int[] freqs = {5,3,3,2};
        
        AutocompleteSystem test = new AutocompleteSystem(sents, freqs);
        
        List<String> t1 = test.input('i');
    }
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        current = root;
        prefix = "";
        
        if (sentences == null || times == null || sentences.length != times.length) return;
        TrieNode crawl = root;
        
        for (int i = 0; i < sentences.length; i++) {
            String sentence = sentences[i];
            int freq = times[i];
            
            insert(sentence, freq);
        }
    }
    
    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c != '#') {
            int index = c == ' ' ? 26 : c - 'a';
            prefix += c;
            
            if (current == null || current.children[index] == null) {
                // If no matching prefixed sentences, return empty, set crawl to null but still record the input
                current = null;
            } else {
                // Move the crawl first
                current = current.children[index];
 
                PriorityQueue<Sentence> maxHeap = current.maxHeap;
                List<Sentence> temp = new ArrayList<>();
                
                // Add the top 3(or less) string into the list to return
                while (!maxHeap.isEmpty() && res.size() < 3) {
                    Sentence top = maxHeap.poll();
                    temp.add(top);
                    res.add(top.sentence);
                }
                
                // Put them back
                for (Sentence item : temp)
                    maxHeap.offer(item);
            }
        } else {
            // when user stopped typing
            if (current == null) {
                // if its a new sentence, easy peasy
                insert(prefix, 1);
            } else {
                if (!current.prefixedSentences.contains(prefix)) insert(prefix, 1);
                else {
                    incrementFreq(prefix);
                }
            }
            prefix = "";
            current = root;
        }
        return res;
    
    }
    
    private void insert(String sentence, int freq) {
        TrieNode crawl = root;
    
        // Traverse down the path of Trie tree, add the set as well as maxheap
        for (char ch : sentence.toCharArray()) {
            crawl.prefixedSentences.add(sentence);
            crawl.maxHeap.offer(new Sentence(sentence, freq));
        
            int index = ch == ' ' ? 26 : ch - 'a';
        
            if (crawl.children[index] == null)
                crawl.children[index] = new TrieNode();
            crawl = crawl.children[index];
        }
    
        // Take care of the leave node
        crawl.prefixedSentences.add(sentence);
        crawl.maxHeap.offer(new Sentence(sentence, freq));
    }
    
    private void incrementFreq(String sentence) {
        TrieNode crawl = root;
        
        for (char ch : sentence.toCharArray()) {
            int index = ch == ' ' ? 26 : ch - 'a';
            updateHeap(crawl.maxHeap, sentence);
            crawl = crawl.children[index];
        }
        
        updateHeap(crawl.maxHeap, sentence);
    }
    
    private void updateHeap(PriorityQueue<Sentence> maxHeap, String sentence) {
        List<Sentence> temp = new ArrayList<>();
        
        while(!maxHeap.isEmpty()) {
            Sentence top = maxHeap.poll();
            if (top.sentence.equals(sentence)) {
                top.freq += 1;
                maxHeap.offer(top);
            } else {
                temp.add(top);
            }
        }
        
        for (Sentence sent : temp)
            maxHeap.offer(sent);
    }
    
    TrieNode root;
    TrieNode current;
    String prefix;
    
    static class TrieNode {
        PriorityQueue<Sentence> maxHeap;
        Set<String> prefixedSentences;
        TrieNode[] children;
        
        public TrieNode() {
            maxHeap = new PriorityQueue<>();
            prefixedSentences = new HashSet<>();
            children = new TrieNode[27];
        }
    }
    
    static class Sentence implements Comparable<Sentence> {
        String sentence;
        int freq;
        
        public Sentence(String sentence, int freq) {
            this.sentence = sentence;
            this.freq = freq;
        }
        
        public int compareTo(Sentence t1) {
            // Same frequency, compare the string -> note invert the compare method for maxHeap
            if (this.freq == t1.freq) return this.sentence.compareTo(t1.sentence);
            else return t1.freq - this.freq;
        }
    }
}
