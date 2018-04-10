public class LargestSumAverage {
    public static void main(String[] args) {
        LargestSumAverage test = new LargestSumAverage();
        
        int[] arr = {1,2,3};
        double res = test.largestSumOfAverages(arr, 5);
    }
    
    public double largestSumOfAverages(int[] A, int K) {
        if (A == null || A.length == 0) return 0;
    
        int len = A.length;
        // Build a presum array
        int[] preSum = new int[len];
        preSum[0] = A[0];
        for (int i = 1; i < len; i++)
            preSum[i] = preSum[i-1] + A[i];
    
        if (K >= len) return preSum[len-1];
    
        double[][] dp = new double[len][K + 1];
        for (int i = 0; i < len; i++)
            dp[i][1] = 1.0 * preSum[i]/(i + 1);
    
        // k range [1, k]
        for (int k = 2; k <= K; k++) {
            // i  as the index of last item to consider
            for (int i = 0; i < len; i++) {
                double max = dp[i][k-1];
            
                // cut means the index of element right in front of the cut
                for (int cut = i - 1; cut >= 0; cut--) {
                    double sumAvg = 1.0 * (preSum[i] - preSum[cut]) / (i - cut) + dp[cut][k-1];
                    max = Math.max(max, sumAvg);
                }
            
                dp[i][k] = max;
            }
        }
    
        return dp[len-1][K];
    }
}
