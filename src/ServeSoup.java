public class ServeSoup {
    public static void main(String[] args) {
        ServeSoup test = new ServeSoup();
        double prob = test.soupServingsDP(100);
    }
    
    public double soupServings(int N) {
        double[] res = new double[1];
        
        soupHelper(N, N, 1.0, res);
        
        return res[0];
    }
    
    private void soupHelper(int ALeft, int BLeft, double prob, double[] res) {
        if (ALeft <= 0 && BLeft > 0) res[0] += prob;
        if (ALeft <= 0 && BLeft <= 0) res[0] += 0.5 * prob;
        if (ALeft <= 0 || BLeft <= 0) return;
        
        soupHelper(ALeft - 100, BLeft, prob * 0.25, res);
        soupHelper(ALeft - 75, BLeft - 25, prob * 0.25, res);
        soupHelper(ALeft - 50, BLeft - 50, prob * 0.25, res);
        soupHelper(ALeft - 25, BLeft - 75, prob * 0.25, res);
    }
    
    public double soupServingsDP(int N) {
        int n = N/25 + (N%25 == 0 ? 0 : 1);
        if (n > 500)
            return 1;
        
        double[][] dp = new double[n+1][n+1];
        
        for (int i = 0; i <= n; i++) dp[0][i] = 1;
        dp[0][0] = 0.5;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                double ans = 0.25 * (dp[M(i-4)][j] + dp[M(i-3)][M(j-1)]
                        + dp[M(i-2)][M(j-2)] + dp[M(i-1)][M(j-3)]);
                dp[i][j] = ans;
            }
        }
        
        return dp[n][n];
    }
    
    private int M(int x) {
        return Math.max(0, x);
    }
    
}
