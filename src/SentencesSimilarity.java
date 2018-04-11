import java.util.*;

public class SentencesSimilarity {
    public static void main(String[] args) {
        SentencesSimilarity test = new SentencesSimilarity();

        String[] words1 = {"great","acting","skills"};
        String[] words2 = {"fine","drama","talent"};
        String[][] pairs = {{"great","fine"}, {"drama","acting"}, {"skills","talent"}};

        boolean res1 = test.areSentencesSimilarTwo(words1, words2, pairs);
    }

    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if (words1 == null || words2 == null || words1.length != words2.length) return false;

        Map<String, Set<String>> map = new HashMap<>();
        for (String[] pair : pairs) {
            Set<String> set1 = map.getOrDefault(pair[0], new HashSet<>());
            set1.add(pair[1]);
            map.put(pair[0], set1);

            Set<String> set2 = map.getOrDefault(pair[1], new HashSet<>());
            set2.add(pair[0]);
            map.put(pair[1], set2);
        }

        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) continue;
            if (!map.get(words1[i]).contains(words2[i])) return false;
        }
        return true;
    }

    static class WordsUnion {
        Map<String, String> parent;
        Map<String, Integer> rank;

        WordsUnion() {
            parent = new HashMap<>();
            rank = new HashMap<>();
        }

        String find(String word) {
            if (!parent.containsKey(word)) {
                parent.put(word, word);
                return word;
            } else {
                while(!word.equals(parent.get(word)))
                    word = parent.get(word);
                return word;
            }
        }

        int getRank(String word) {
            if (rank.containsKey(word)) return rank.get(word);
            else rank.put(word, 0);
            return 0;
        }

        void union(String str1, String str2) {
            String parent1 = find(str1), parent2 = find(str2);
            int rank1 = getRank(parent1), rank2 = getRank(parent2);
            if (rank2 > rank1) {
                String temp = str1;
                str1 = str2;
                str2 = temp;
            }

            if (rank1 == rank2)
                rank.put(parent1, ++rank1);

            parent.put(parent2, parent1);
        }
    }

    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1 == null || words2 == null || words1.length != words2.length) return false;

        WordsUnion dsu = new WordsUnion();
        for (String[] pair : pairs)
            dsu.union(pair[0], pair[1]);

        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) continue;

            String parent1 = dsu.find(words1[i]);
            String parent2 = dsu.find(words2[i]);
            if (!parent1.equals(parent2)) return false;
        }
        return true;
    }
}
