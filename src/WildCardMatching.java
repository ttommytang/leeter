public class WildCardMatching {
    public static void main(String[] args) {
        WildCardMatching test = new WildCardMatching();
        boolean r1 = test.isMatch("mississippi", "m??*ss*?i*pi");
    }
    
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;
        
        for (int i = 1; i <= p.length(); i++) {
            for (int j = 0; j <= s.length(); j++) {
                if (j == 0) {
                    if (p.charAt(i-1) == '*') dp[i][j] = dp[i-1][j];
                    else dp[i][j] = false;
                    continue;
                }
                
                if (p.charAt(i-1) == '?') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (p.charAt(i-1) == '*') {
                    // Need to count pre-match needed for pre'*' pattern
                    int pre = 0;
                    while(pre <= s.length() && !dp[i-1][pre]) pre++;
                    
                    for (; pre <= s.length(); pre++)
                        dp[i][pre] = true;
                    break;
                } else {
                    if (s.charAt(j-1) == p.charAt(i-1))
                        dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        return dp[p.length()][s.length()];
    }
}
