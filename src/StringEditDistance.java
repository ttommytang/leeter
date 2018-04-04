public class StringEditDistance {
    public static void main(String[] args) {
    
    }
    
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null) return false;
        
        // If s and t are of same length, than they can have only one char difference
        if (s.length() == t.length()) {
            int count = 0;
            for (int i = 0; i < s.length(); i++)
                if (s.charAt(i) != t.charAt(i)) count++;
            return count == 1;
        } else if (Math.abs(s.length() - t.length()) == 1){
            String longer = s.length() > t.length() ? s : t;
            String shorter = s.length() > t.length()? t : s;
            
            int i = 0;
            while(i < shorter.length() && longer.charAt(i) == shorter.charAt(i)) i++;
            while(i < shorter.length() && longer.charAt(i+1) == shorter.charAt(i)) i++;
            return i == shorter.length();
        } else
            return false;
    }
    
    public int minDistance(String word1, String word2) {
        // TODO: implement word distance method again
        if (word1 == null || word2 == null) return 0;
        int l1 = word1.length(), l2 = word2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        
        for (int i = 0; i <= l1; i++) {
            for (int j = 0; j <= l2; j++) {
                // First of all, if any of the string to consider is empty, than only way to match is insert/delete whole length
                if (i == 0 || j == 0) {dp[i][j] = i == 0 ? j : i; continue;}
                if (word1.charAt(i-1) == word2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else {
                    int min = Integer.MAX_VALUE;
                    min = Math.min(min, 1 + dp[i-1][j-1]);
                    min = Math.min(min, 1 + dp[i][j-1]);
                    min = Math.min(min, 1 + dp[i-1][j]);
                    dp[i][j] = min;
                }
            }
        }
        return dp[l1][l2];
    }
}
