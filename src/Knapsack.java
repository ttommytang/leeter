public class Knapsack {
    // Main goal: get comfortable with the Bi-branch DP problem -> pick or not for each item to achieve some optimized solution
    public static void main(String[] args) {
        Knapsack test = new Knapsack();
        int[] arr1 = {2,3,5,7};
        int[] arr2 = {1,5,2,4};
        
        int res1 = test.knapsackWithUnlimitedItems(arr1, arr2, 10);
    }
    
    // Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?
    int knapsackSizeOnly(int[] sizes, int volume) {
        /*
        for index of item size array - iS
          for target volume - from 0 to V
            if A[iS] > v -> must not add this item => dp[iS][v] = dp[iS-1][v]
            else consider add or not add => dp[iS][v] = Math.max(dp[iS-1][v - A[iS]], dp[iS-1][v]);
         */
        
        /*
        int[][] dp = new int[sizes.length + 1][volume + 1];
        
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0 || j == 0) dp[i][j] = 0;
                else if (sizes[i-1] > j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = Math.max(dp[i-1][j - sizes[i-1]] + sizes[i-1], dp[i-1][j]);
            }
        }
        return dp[sizes.length][volume];
        */
        
        // To optimize the space, inner loop need to be inverted so we just loop on the same array as for last status
        int[] dp = new int[volume + 1];
    
        for (int i = 0; i < sizes.length; i++) {
            for (int j = volume; j > 0; j--) {
                if (sizes[i] <= j)
                    dp[j] = Math.max(dp[j - sizes[i]] + sizes[i], dp[j]);
            }
        }
        return dp[volume];
    }
    
    int knapsackSizeOnlyDFS(int[] size, int vol) {
        int[] mem = new int[2];
        knapsackSizeOnlyDFSRecursion(size, vol, 0, mem);
        return mem[1];
    }
    
    private void knapsackSizeOnlyDFSRecursion(int[] size, int vol, int numOfItem, int[] mem) {
        if (mem[0] > vol) return;
        if (mem[0] > mem[1]) mem[1] = mem[0];
        if (numOfItem >= size.length) return;
    
        mem[0] += size[numOfItem];
        knapsackSizeOnlyDFSRecursion(size, vol, numOfItem + 1, mem);
        mem[0] -= size[numOfItem];
        knapsackSizeOnlyDFSRecursion(size, vol, numOfItem + 1, mem);
    }
    
    // Now we add the item values into the problem
    int knapsackWithValue(int[] sizes, int[] values, int volume) {
        if (sizes == null || values == null || sizes.length != values.length || volume == 0) return 0;
        
        int[] dp = new int[volume+1];
        
        for (int i = 0; i < sizes.length; i++) {
            for (int j = volume; j > 0; j--) {
                if (sizes[i] <= j)
                    dp[j] = Math.max(dp[j], dp[j - sizes[i]] + values[i]);
            }
        }
        return dp[volume];
    }
    
    int knapsackWithUnlimitedItems(int[] sizes, int[] values, int volume) {
        if (sizes == null || values == null || sizes.length != values.length || volume == 0) return 0;
    
        int[] dp = new int[volume+1];
    
        for (int i = 0; i < sizes.length; i++) {
            for (int j = 1; j <= volume; j++) {
                if (sizes[i] <= j)
                    dp[j] = Math.max(dp[j], dp[j - sizes[i]] + values[i]);
            }
        }
        return dp[volume];
    }
    
    int knapsackFindSolutionsWithUnlimitedItems(int[] sizes, int volume) {
        // Find number of solutions to fill the bag exactly, each item has unlimited quantity
        if (sizes == null || sizes.length == 0 || volume == 0) return 0;
        
        int[] dp = new int[volume+1];
        dp[0] = 1;
    
        for (int size : sizes) {
            for (int j = 1; j <= volume; j++)
                if (j == size) dp[j]++;
                else if (j > size) dp[j] += dp[j - size];
        }
        
        return dp[volume];
    }
    
    int knapsackFindSolutionsWithSingularItems(int[] sizes, int volume) {
        if (sizes == null || sizes.length == 0 || volume == 0) return 0;
        
        int[] dp = new int[volume+1];
        dp[0] = 1;
        
        for (int size : sizes) {
            for (int j = volume; j >= 0; j++)
                if (j >= size)
                    dp[j] += dp[j - size];
        }
        return dp[volume];
    }
    
    // K-sum problem - find sub-sequence of length K that sums up to T
//    int kSumDP(int[] arr, int K, int T) {
//
//    }
}
