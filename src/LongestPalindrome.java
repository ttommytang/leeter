public class LongestPalindrome {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;

        int len = s.length(), maxLen = 0;
        boolean[][] mem = new boolean[len][len];
        String res = "";

        for (int l = 1; l <= len; l++) {
            for (int from = 0; from  <= len - l; from++) {
                int to = from + l - 1;
                if (l == 1) {
                    mem[from][to] = true;
                } else if (l == 2) {
                    mem[from][to] = s.charAt(from) == s.charAt(to);
                } else {
                    if (s.charAt(from) == s.charAt(to))
                        mem[from][to] = mem[from+1][to-1];
                    else
                        mem[from][to] = false;
                }

                if (mem[from][to] && l > maxLen) {
                    maxLen = l;
                    res = s.substring(from, to + 1);
                }
            }
        }
        return res;
    }
}
