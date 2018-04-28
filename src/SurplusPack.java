import java.util.Arrays;

public class SurplusPack {
    public static void main(String[] args) {
    }
    
    /**
     * @param k1: The coefficient of A
     * @param k2: The  coefficient of B
     * @param c: The volume of backpack
     * @param n: The amount of A
     * @param m: The amount of B
     * @param a: The volume of A
     * @param b: The volume of B
     * @return: Return the max value you can get
     */
    public long getMaxValue(int k1, int k2, int c, int n, int m, int[] a, int[] b) {
        if (a == null || b == null) return 0;
    
        Arrays.sort(a);
        Arrays.sort(b);
        long[] sumA = new long[n], sumB = new long[m];
        sumA[0] = a[0]; sumB[0] = b[0];
        
        for (int i = 1; i < n; i++)
            sumA[i] = sumA[i-1] + a[i];
        for (int i = 1; i < m; i++)
            sumB[i] = sumB[i-1] + b[i];
        
        long max = Long.MIN_VALUE;
        
        long[][] dp = new long[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            if (sumA[i - 1] <= c) {
                dp[i][0] = dp[i - 1][0] + k1 * (c - sumA[i - 1]);
                max = Math.max(dp[i][0], max);
            }
        }
        
        for (int i = 1; i <= m; i++) {
            if (sumB[i - 1] <= c) {
                dp[0][i] = dp[0][i - 1] + k2 * (c - sumB[i - 1]);
                max = Math.max(dp[0][i], max);
            }
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (sumA[i-1] + sumB[j-1] > c) break;
                dp[i][j] = Math.max(dp[i-1][j] + k1 * (c - sumA[i-1] - sumB[j-1]), dp[i][j-1] + k2 * (c - sumA[i-1] - sumB[j-1]));
                max = Math.max(dp[i][j], max);
            }
        }
        
        return max;
    }
}
