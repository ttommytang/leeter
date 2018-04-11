import java.util.*;

public class RearrangeString {
    public static void main(String[] args) {
        RearrangeString test = new RearrangeString();
        String str1 = "aaaabbbcde";
        String res1 = test.rearrangeString(str1, 3);

        System.out.println(res1);
    }

    public String reorganizeString(String S) {
        if (S == null || S.length() == 0) return "";

        int len = S.length();
        int[] freqs = new int[26];
        for (char ch : S.toCharArray()) {
            freqs[ch - 'a'] += 1;
            if (freqs[ch - 'a'] > (len + 1)/2) return "";
        }

        PriorityQueue<CharCount> maxHeap = new PriorityQueue<>();
        for (int i = 0; i < 26; i++) {
            if (freqs[i] > 0) {
                maxHeap.offer(new CharCount((char)('a' + i), freqs[i]));
            }
        }

        StringBuilder res = new StringBuilder();

        while (maxHeap.size() >= 2) {
            CharCount top = maxHeap.poll();
            CharCount next = maxHeap.poll();

            res.append(top.ch); res.append(next.ch);
            if (--top.count > 0) maxHeap.offer(top);
            if (--next.count > 0) maxHeap.offer(next);
        }

        if (!maxHeap.isEmpty()) res.append(maxHeap.poll().ch);
        return res.toString();
    }

    static class CharCount implements Comparable<CharCount>{
        char ch;
        int count;
        CharCount(char cr, int freq) {
            ch = cr;
            count = freq;
        }
        public int compareTo(CharCount other) {
            if (this.count == other.count) return (int)(this.ch - other.ch);
            return other.count - this.count;
        }
    }

    public String rearrangeString(String s, int k) {
        if (s == null || s.length() == 0) return "";
        if (k < 2) return s;

        int len = s.length();
        int[] freqs = new int[26];
        // Mathematical relation:  max <= (len + (k-1)) / k
        for (char ch : s.toCharArray()) {
            freqs[ch - 'a'] += 1;
            if (freqs[ch - 'a'] > (len + (k-1))/k) return "";
        }

        PriorityQueue<CharCount> maxHeap = new PriorityQueue<>();
        for (int i = 0; i < 26; i++) {
            if (freqs[i] > 0) {
                maxHeap.offer(new CharCount((char)('a' + i), freqs[i]));
            }
        }

        StringBuilder res = new StringBuilder();
        while(maxHeap.size() >= k) {
            List<CharCount> mem = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                CharCount peek = maxHeap.poll();
                res.append(peek.ch);
                peek.count -= 1;
                if (peek.count > 0) mem.add(peek);
            }

            for (CharCount left : mem)
                maxHeap.offer(left);
        }

        while(!maxHeap.isEmpty()) {
            CharCount peek = maxHeap.poll();
            if (peek.count > 1) return "";
            res.append(peek.ch);
        }

        return res.toString();
    }
}
