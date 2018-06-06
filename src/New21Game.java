public class New21Game {
    public static void main(String[] args) {
        New21Game test = new New21Game();
        
        double t1 = test.new21GameDP(21, 17, 10);
    }
    
    public double new21GameDP(int N, int K, int W) {
        double[] dp = new double[N+2];
        for (int i = K; i <= N; i++)
            dp[i] = 1.0;
        
        double preSum = 0;
        for (int j = 1; j <= W; j++) {
            preSum += dp[Math.min(K+j, N+1)];
        }
        
        for (int i = K-1; i >= 0; i--) {
            double curSum = preSum + dp[i+1] - dp[Math.min(i+1+W, N+1)];
            dp[i] = curSum/W;
            preSum = curSum;
        }
        return dp[0];
    }
    
    public double new21Game(int N, int K, int W) {
        double[] memo = new double[N+1];
        return f(0, N, K, W, memo);
    }
    
    public double f(int x, int N, int K, int W, double[] memo) {
        if (x >= K && x <= N)
            return 1.0;
        if (x > N)
            return 0;
        
        if (memo[x] > 0)
            return memo[x];
        
        double prob = 0;
        for (int c = 1; c <= W; c++) {
            prob += f(x+c, N, K, W, memo);
        }
        memo[x] = prob/W;
        
        return prob/W;
    }
}
