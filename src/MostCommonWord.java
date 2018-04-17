import java.util.*;

public class MostCommonWord {
    public static void main(String[] args) {

    }

    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0)
            return "";

        Set<String> ban = new HashSet<>();
        Collections.addAll(ban, banned);

        String[] words = paragraph.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

        String res = ""; int maxFreq = 0;
        Map<String, Integer> map = new HashMap<>();

        for (String word : words) {
            if (ban.contains(word)) continue;

            map.put(word, map.getOrDefault(word, 0) +1);
            int newFreq = map.get(word);
            if (newFreq >= maxFreq) {
                res = newFreq == maxFreq && res.compareTo(word) > 0 ? res : word;
                maxFreq = newFreq;
            }
        }

        return res;
    }

}
