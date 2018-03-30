import java.util.*;

public class DNA {
    public static void main(String[] args) {
        DNA test = new DNA();

        List<String> dups = test.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");

        for (String s : dups) {
            System.out.println(s);
        }
    }

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 11) return res;

        Set<Integer> hashed = new HashSet<>();
        Set<Integer> output = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 1); map.put('C', 2); map.put('G', 3); map.put('T', 4);

        int hash = 0;
        for (int i = 0; i < 10; i++) {
            hash = hash * 5 + map.get(s.charAt(i));
        }

        hashed.add(hash);
        int root = (int)Math.pow(5, 9);

        for (int i = 1; i <= s.length() - 10; i++) {
            int newHash  = hash % root;
            newHash *= 5;
            newHash += map.get(s.charAt(i + 9));

            if (hashed.contains(newHash)) {
                if (!output.contains(newHash)) {
                    res.add(s.substring(i, i + 10));
                    output.add(newHash);
                }
            } else {
                hashed.add(newHash);
            }
            hash = newHash;
        }

        return res;

    }
}
