public class Tiling {
    public static void main(String[] args) {
        Tiling test = new Tiling();
    }
    
    public int numTilings(int N) {
        if (N == 0) return 0;
        if (N < 3) return N;
        
        // The solution will be based on the mathematically relation T(N) = 2 * T(N-1) + T(N - 3);
        int[] dp = new int[N + 1];
        dp[0] = 1; dp[1] = 1; dp[2] = 2;
        for (int i = 3; i <= N; i++)
            dp[i] = 2*dp[i-1] + dp[i-3];
        
        return dp[N];
    }
}
