import java.util.*;

public class ImageSmoother {
    public static void main(String[] args) {
        ImageSmoother test = new ImageSmoother();
        
//        int[] arr1 = {1, 0};
//        int[] arr2 = {0, 0, 0};
//
//        System.out.println(test.findMaxConsecutiveOnesWithOneFlip(arr1));
//        System.out.println(test.findMaxConsecutiveOnesWithOneFlip(arr2));
        
        int[][] M = {{1, 0}};
        int[][] smoothed = test.imageSmoother(M);
        
    }
    
    public int[][] imageSmoother(int[][] M){
        /* Kinda leverage the logic of longest consecutive ones
        for (i, j) element check the 3*3 square which centered at itself, -> 1 is all 1 otherwise 0
        General case -> DP[i-1][j+1], DP[i][j+1], DP[i+1][j+1] must all >= 3
        corner cases: one by one check or expand one layer out as if filled by ones
         */
        int R = M.length, C = M[0].length;
        int[][] ans = new int[R][C];
    
        for (int r = 0; r < R; ++r)
            for (int c = 0; c < C; ++c) {
                int count = 0;
                for (int nr = r-1; nr <= r+1; ++nr)
                    for (int nc = c-1; nc <= c+1; ++nc) {
                        if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                            ans[r][c] += M[nr][nc];
                            count++;
                        }
                    }
                ans[r][c] /= count;
            }
        return ans;
    }
    
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int longest = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) continue;
            if (i > 0) nums[i] += nums[i-1];
            longest = Math.max(longest, nums[i]);
        }
        
        return longest;
    }
    
    public int findMaxConsecutiveOnesWithOneFlip(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // Let's try something like finding island
        List<Integer> postCounts = new ArrayList<>();
        int i = 0;
        for (; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (i != 0) postCounts.add(nums[i-1]);
                else postCounts.add(0);
            } else {
                nums[i] += (i > 0 ? nums[i-1] : 0);
            }
        }
        // Add the last island if tail is missed
        postCounts.add(nums[i-1]);
        
        int max = 0, j = 0;
        for (; j < postCounts.size() - 1; j++) {
            max = Math.max(max, postCounts.get(j));
            max = Math.max(max, postCounts.get(j+1));
            max = Math.max(max, postCounts.get(j) + postCounts.get(j+1) + 1);
        }
        max = Math.max(max, postCounts.get(j));
        
        return max;
    }
}
